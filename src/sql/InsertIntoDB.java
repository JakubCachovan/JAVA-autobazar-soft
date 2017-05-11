/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;
import autobazar.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Acer
 */
public class InsertIntoDB {
    /**
     * Speacialne pre vkladanie inzeratu
     * @param idInzeratu
     * @param dbPath
     * @return 
     */
    public static int zistiIdVozidla(int idInzeratu, String dbPath){
        int lastIdVozidla = -1;
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){         
            PreparedStatement state = con.prepareStatement("SELECT max(id_vozidla) as maxId from Vozidlo;");
            ResultSet rs = state.executeQuery();
            while(rs.next())
            {
                lastIdVozidla = Integer.parseInt(rs.getString("maxId"));
            }
            con.close();  
            return lastIdVozidla;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return lastIdVozidla;
    }
    
    public static boolean insertInzerat(Inzerat inzerat, String dbPath){      
        if(isNovyPredajca(inzerat.getPredajca().getEmail(), dbPath)){
            //vlozenie predajcu do DB
            if(!insertPredajca(inzerat.getPredajca(), dbPath)){
                JOptionPane.showMessageDialog(null, "Chyba pri pridavani predajcu do DB");
                return false;
            }
        }      
        switch(inzerat.getInstanceKategoria()){
            case 1:
                //Auto
                Automobil auto = (Automobil)inzerat.getKategoria();
                if(!insertAutomobil(auto, dbPath))
                    JOptionPane.showMessageDialog(null, "Chyba pri pridavani automobilu do DB");
                break;
            case 2:
                //Autobus
                Autobus autobus = (Autobus)inzerat.getKategoria();
                if(!insertAutobus(autobus, dbPath))
                    JOptionPane.showMessageDialog(null, "Chyba pri pridavani autobusu do DB");
                break;
            case 3:
                //Motocykel
                Motocykel moto = (Motocykel)inzerat.getKategoria();
                if(!insertMotocykel(moto, dbPath))
                    JOptionPane.showMessageDialog(null, "Chyba pri pridavani motocyklu do DB");
                break;
            case 4:
                //Nakladne
                NakladneAuto nakladne = (NakladneAuto)inzerat.getKategoria();
                if(!insertNakladne(nakladne, dbPath))
                    JOptionPane.showMessageDialog(null, "Chyba pri pridavani nakladneho auta do DB");
                    
                break;            
        }
        int idVozidla = -1;
        idVozidla = zistiIdVozidla(inzerat.getID(), dbPath);
        if(idVozidla == -1){
            JOptionPane.showMessageDialog(null, "Chyba pri zistovani id vozidla z DB");
            return false;
        }
        // vlozenie do tabulky inzerat
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement("INSERT INTO Inzerat (id_inzeratu, id_vozidla, datum, stav, email) VALUES (?,?,?,?,?);");
            state.setString(1, inzerat.getID()+"");
            state.setString(2, idVozidla+"");
            state.setString(3, inzerat.getDatumVytvorenia().toString());
            state.setString(4, inzerat.getStav().name());
            state.setString(5, inzerat.getPredajca().getEmail());
            state.executeUpdate();  
            con.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    
    public static boolean insertAutomobil(Automobil auto, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement(
                    "INSERT INTO Vozidlo (id_kategoria, cena, znacka, model, pocetKm, vykon, rokVyroby, palivo, stav_vozidla, popis, karoseria, pocetDveri, pocetSedadiel)\n" +
                                    "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");
            state.setString(1, "1");
            state.setString(2, auto.getCena()+"");
            state.setString(3, auto.getZnacka());
            state.setString(4, auto.getModel());
            state.setString(5, auto.getNajazdeneKM()+"");
            state.setString(6, auto.getVykon()+"");
            state.setString(7, auto.getRokVyroby()+"");
            state.setString(8, auto.getPalivo());
            state.setString(9, auto.getStav().name());
            state.setString(10, auto.getPopis());
            state.setString(11, auto.getKaroseria());
            state.setString(12, auto.getPocetDveri()+"");
            state.setString(13, auto.getPocetSedadiel()+"");
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    public static boolean insertMotocykel(Motocykel moto, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement(
                    "INSERT INTO Vozidlo (id_kategoria, cena, znacka, model, pocetKm, vykon, rokVyroby, palivo, stav_vozidla, popis, karoseria, objemValcov)\n" +
                                    "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
            state.setString(1, "2");
            state.setString(2, moto.getCena()+"");
            state.setString(3, moto.getZnacka());
            state.setString(4, moto.getModel());
            state.setString(5, moto.getNajazdeneKM()+"");
            state.setString(6, moto.getVykon()+"");
            state.setString(7, moto.getRokVyroby()+"");
            state.setString(8, moto.getPalivo());
            state.setString(9, moto.getStav().name());
            state.setString(10, moto.getPopis());
            state.setString(11, moto.getDruh());
            state.setString(12, moto.getObjemValcov()+"");
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    public static boolean insertAutobus(Autobus autobus, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement(
                    "INSERT INTO Vozidlo (id_kategoria, cena, znacka, model, pocetKm, vykon, rokVyroby, palivo, stav_vozidla, popis, karoseria, nadstavba, pocetSedadiel, batozPriestor, vlastnostiSedadiel, normaEmisii)\n" +
                                    "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            state.setString(1, "4");
            state.setString(2, autobus.getCena()+"");
            state.setString(3, autobus.getZnacka());
            state.setString(4, autobus.getModel());
            state.setString(5, autobus.getNajazdeneKM()+"");
            state.setString(6, autobus.getVykon()+"");
            state.setString(7, autobus.getRokVyroby()+"");
            state.setString(8, autobus.getPalivo());
            state.setString(9, autobus.getStav().name());
            state.setString(10, autobus.getPopis());
            state.setString(11, autobus.getDruh());
            state.setString(12, autobus.getNadstavba());
            state.setString(13, autobus.getPocetMiestNaSedenie()+"");
            state.setString(14, autobus.getBatozinovyPriestor().vratRetazec());
            state.setString(15, autobus.getVlastnostiSedadiel().vratRetazec());
            state.setString(16, autobus.getNormaEmisii());
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    public static boolean insertNakladne(NakladneAuto nakladne, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement(
                    "INSERT INTO Vozidlo (id_kategoria, cena, znacka, model, pocetKm, vykon, rokVyroby, palivo, stav_vozidla, popis, karoseria, hmotnostVozidla, normaEmisii, nosnost)\n" +
                                    "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            state.setString(1, "3");
            state.setString(2, nakladne.getCena()+"");
            state.setString(3, nakladne.getZnacka());
            state.setString(4, nakladne.getModel());
            state.setString(5, nakladne.getNajazdeneKM()+"");
            state.setString(6, nakladne.getVykon()+"");
            state.setString(7, nakladne.getRokVyroby()+"");
            state.setString(8, nakladne.getPalivo());
            state.setString(9, nakladne.getStav().name());
            state.setString(10, nakladne.getPopis());
            state.setString(11, nakladne.getDruh());
            state.setString(12, nakladne.getHmotnostVozidla()+"");
            state.setString(13, nakladne.getNormaEmisii()+"");
            state.setString(14, nakladne.getNosnost()+"");
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    
    public static boolean isNovyPredajca(String email, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){
            int pocet = -1;
            PreparedStatement state = con.prepareStatement("Select count(*) as pocet FROM Predajca where email=?");
            state.setString(1, email);
            ResultSet rs = state.executeQuery();
            while(rs.next())
            {
                pocet = Integer.parseInt(rs.getString("pocet"));
            }
            con.close();  
            return pocet == 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
    
    public static boolean insertPredajca(Predajca predajca, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){                               
            PreparedStatement state = con.prepareStatement("INSERT INTO Predajca (email, meno, priezvisko, telefon, lokalita) VALUES (?,?,?,?,?);");
            state.setString(1, predajca.getEmail());
            state.setString(2, predajca.getMeno());
            state.setString(3, predajca.getPriezvisko());
            state.setString(4, predajca.getTelefon());
            state.setString(5, predajca.getLokalita());
            state.executeUpdate();
            con.close();      
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return false;
    }
}
