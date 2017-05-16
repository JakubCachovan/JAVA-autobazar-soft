package sql;

import autobazar.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Trieda pre úpravu Autobazaru v databáze.
 * @author Jakub Cachovan
 */
public class UpdateDB {
            
    /**
     * Upravenie stavu inzerátu na "Predane"  podla id inzerátu.
     * @param idInzeratu - id inzeratu
     * @param dbPath - cesta k DB
     * @return true/false
     */
    public static boolean upravitStavInzeratu(String idInzeratu, String dbPath){      
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){               
            PreparedStatement state = con.prepareStatement("UPDATE Inzerat SET stav='Predane' WHERE id_inzeratu=?;");
            state.setString(1, idInzeratu);
            state.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
    /**
     * Upravenie predajcu v DB.
     * @param predajca - objekt typu Predajca
     * @param dbPath - cesta k DB
     * @return true/false
     */
    public static boolean upravitPredajcu(Predajca predajca, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){
            PreparedStatement state = con.prepareStatement("UPDATE Predajca SET email=?,meno=?,priezvisko=?,telefon=?,lokalita=? WHERE email=?;");
            state.setString(1, predajca.getEmail());
            state.setString(2, predajca.getMeno());
            state.setString(3, predajca.getPriezvisko());
            state.setString(4, predajca.getTelefon());
            state.setString(5, predajca.getLokalita());
            state.setString(6, predajca.getEmail());
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    
    /**
     * Upravenie inzeratu v DB.
     * @param inzerat - inzerat
     * @param dbPath - cesta k DB
     * @return true/false
     */
    public static boolean upravitInzerat(Inzerat inzerat, String dbPath){
        int idVozidla = -1;
        idVozidla = LoaderDB.zistiIdVozidla(inzerat.getID(), dbPath);
        if(idVozidla == -1) return false;
        
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){               
            PreparedStatement state = con.prepareStatement("UPDATE Inzerat SET id_inzeratu=?, datum=?, email=?, stav=?, id_vozidla=? WHERE id_inzeratu=?;");
            state.setString(1, inzerat.getID()+"");
            state.setString(2, inzerat.getDatumVytvorenia().toString());
            state.setString(3, inzerat.getPredajca().getEmail());
            state.setString(4, inzerat.getStav().name());
            state.setString(5, idVozidla+"");
            state.setString(6, inzerat.getID()+"");
            state.executeUpdate();
            con.close();
            
            upravitPredajcu(inzerat.getPredajca(), dbPath);
            switch(inzerat.getInstanceKategoria()){
                case 1:
                //Auto
                Automobil auto = (Automobil)inzerat.getKategoria();
                if(!upravitAutomobil(idVozidla, auto, dbPath))
                    JOptionPane.showMessageDialog(null, "Chyba pri upravovani automobilu v DB");
                break;
            case 2:
                //Autobus
                Autobus autobus = (Autobus)inzerat.getKategoria();
                if(!upravitAutobus(idVozidla, autobus, dbPath))
                    JOptionPane.showMessageDialog(null, "Chyba pri upravovani autobusu v DB");
                break;
            case 3:
                //Motocykel
                Motocykel moto = (Motocykel)inzerat.getKategoria();
                if(!upravitMotocykel(idVozidla, moto, dbPath))
                    JOptionPane.showMessageDialog(null, "Chyba pri upravovani motocyklu v DB");
                break;
            case 4:
                //Nakladne
                NakladneAuto nakladne = (NakladneAuto)inzerat.getKategoria();
                if(!upravitNakladne(idVozidla, nakladne, dbPath))
                    JOptionPane.showMessageDialog(null, "Chyba pri upravovani nakladneho auta v DB");                  
                break;   
            }         
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }  
    
    /**
     * Upravenie automobilu v DB.
     * @param idVozidla - id vozidla
     * @param auto - objekt typu Automobil
     * @param dbPath - cesta k DB
     * @return  true/false
     */
    public static boolean upravitAutomobil(int idVozidla, Automobil auto, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement(
                    "UPDATE Vozidlo SET cena=?, znacka=?, model=?, pocetKm=?, vykon=?, rokVyroby=?, palivo=?, stav_vozidla=?, popis=?, karoseria=?, pocetDveri=?, pocetSedadiel=? WHERE id_vozidla=?;");
            state.setString(1, auto.getCena()+"");
            state.setString(2, auto.getZnacka());
            state.setString(3, auto.getModel());
            state.setString(4, auto.getNajazdeneKM()+"");
            state.setString(5, auto.getVykon()+"");
            state.setString(6, auto.getRokVyroby()+"");
            state.setString(7, auto.getPalivo());
            state.setString(8, auto.getStav().name());
            state.setString(9, auto.getPopis());
            state.setString(10, auto.getKaroseria());
            state.setString(11, auto.getPocetDveri()+"");
            state.setString(12, auto.getPocetSedadiel()+"");
            state.setString(13, idVozidla+"");
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    
    /**
     * Upravenie motocyklu v DB.
     * @param idVozidla - id vozidla
     * @param moto - objekt typu Motocykel
     * @param dbPath - cesta k DB
     * @return true/false
     */
    public static boolean upravitMotocykel(int idVozidla, Motocykel moto, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement(
                    "UPDATE Vozidlo SET cena=?, znacka=?, model=?, pocetKm=?, vykon=?, rokVyroby=?, palivo=?, stav_vozidla=?, popis=?, karoseria=?, objemValcov=? WHERE id_vozidla=?;");
            state.setString(1, moto.getCena()+"");
            state.setString(2, moto.getZnacka());
            state.setString(3, moto.getModel());
            state.setString(4, moto.getNajazdeneKM()+"");
            state.setString(5, moto.getVykon()+"");
            state.setString(6, moto.getRokVyroby()+"");
            state.setString(7, moto.getPalivo());
            state.setString(8, moto.getStav().name());
            state.setString(9, moto.getPopis());
            state.setString(10, moto.getDruh());
            state.setString(11, moto.getObjemValcov()+"");
            state.setString(12, idVozidla+"");
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    
    /**
     * Upravenie autobusu v DB.
     * @param idVozidla - id vozidla
     * @param autobus - objekt typu Autobus
     * @param dbPath - cesta k DB
     * @return true/false
     */
    public static boolean upravitAutobus(int idVozidla, Autobus autobus, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement(
                    "UPDATE Vozidlo SET cena=?, znacka=?, model=?, pocetKm=?, vykon=?, rokVyroby=?, palivo=?, stav_vozidla=?, popis=?, karoseria=?, nadstavba=?, pocetSedadiel=?, batozPriestor=?, vlastnostiSedadiel=?, normaEmisii=? WHERE id_vozidla=?;");
            state.setString(1, autobus.getCena()+"");
            state.setString(2, autobus.getZnacka());
            state.setString(3, autobus.getModel());
            state.setString(4, autobus.getNajazdeneKM()+"");
            state.setString(5, autobus.getVykon()+"");
            state.setString(6, autobus.getRokVyroby()+"");
            state.setString(7, autobus.getPalivo());
            state.setString(8, autobus.getStav().name());
            state.setString(9, autobus.getPopis());
            state.setString(10, autobus.getDruh());
            state.setString(11, autobus.getNadstavba());
            state.setString(12, autobus.getPocetMiestNaSedenie()+"");
            state.setString(13, autobus.getBatozinovyPriestor().vratRetazec());
            state.setString(14, autobus.getVlastnostiSedadiel().vratRetazec());
            state.setString(15, autobus.getNormaEmisii());
            state.setString(16, idVozidla+"");
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    
    /**
     * Upravenie nakladneho auta v DB.
     * @param idVozidla - id vozidla
     * @param nakladne - objekt typu NakladneAuto
     * @param dbPath - cesta k Db
     * @return true/false
     */
    public static boolean upravitNakladne(int idVozidla, NakladneAuto nakladne, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement(
                    "UPDATE Vozidlo SET cena=?, znacka=?, model=?, pocetKm=?, vykon=?, rokVyroby=?, palivo=?, stav_vozidla=?, popis=?, karoseria=?, hmotnostVozidla=?, normaEmisii=?, nosnost=?\n" +
                                    "  WHERE id_vozidla=?;");
            state.setString(1, nakladne.getCena()+"");
            state.setString(2, nakladne.getZnacka());
            state.setString(3, nakladne.getModel());
            state.setString(4, nakladne.getNajazdeneKM()+"");
            state.setString(5, nakladne.getVykon()+"");
            state.setString(6, nakladne.getRokVyroby()+"");
            state.setString(7, nakladne.getPalivo());
            state.setString(8, nakladne.getStav().name());
            state.setString(9, nakladne.getPopis());
            state.setString(10, nakladne.getDruh());
            state.setString(11, nakladne.getHmotnostVozidla()+"");
            state.setString(12, nakladne.getNormaEmisii()+"");
            state.setString(13, nakladne.getNosnost()+"");
            state.setString(14, idVozidla+"");
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
}