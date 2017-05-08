/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import autobazar.Autobazar;
import autobazar.Autobus;
import autobazar.Automobil;
import autobazar.BatozinovyPriestor;
import autobazar.Inzerat;
import autobazar.Kategoria;
import autobazar.Motocykel;
import autobazar.NakladneAuto;
import autobazar.Predajca;
import autobazar.StavInzeratu;
import autobazar.StavVozidla;
import autobazar.VlastnostiSedadiel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class LoaderDB {
    
    public static int zistiIdVozidla(int idInzeratu, String dbPath){
        int IdVozidla = -1;
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){         
            PreparedStatement state = con.prepareStatement("SELECT id_vozidla from Inzerat where id_inzeratu=?;");
            state.setString(1, idInzeratu+"");
            ResultSet rs = state.executeQuery();
            while(rs.next())
            {
                IdVozidla = Integer.parseInt(rs.getString("id_vozidla"));
            }
            con.close();  
            return IdVozidla;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        return IdVozidla;
    }
    
    public static int getKategoriaInstance(int idInzeratu, String dbPath){
        int idKategoria = -1;
        try (Connection con = ConnectToDB.ConnectDB(dbPath);) {           
            Statement state = con.createStatement();
            String sql = "SELECT id_kategoria FROM Vozidlo JOIN Inzerat USING (id_vozidla) WHERE id_inzeratu='"+idInzeratu+"';";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {                
                idKategoria = Integer.parseInt(rs.getString("id_kategoria"));
            }            
            con.close();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return idKategoria;
    }
    
    public static Autobazar LoadFromDB(String dbPath){
        
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){
            Autobazar autobazar = new Autobazar();    
            Statement state = con.createStatement();
            String sql = "SELECT * from Predajca;";
            ResultSet rs = state.executeQuery(sql);
  
            //pridanie predajcov
            ArrayList<Predajca> zoznamPredajcov = new ArrayList<>();
            while(rs.next()){
                Predajca p = new Predajca(rs.getString("email"),rs.getString("lokalita") , rs.getString("meno"),
                        rs.getString("priezvisko"), rs.getString("telefon"));
                zoznamPredajcov.add(p);
            }
            autobazar.setZoznamPredajcov(zoznamPredajcov);
            //pridanie inzeratov 
            sql = "SELECT * from Inzerat;";
            rs = state.executeQuery(sql);
            ArrayList<Inzerat> zoznamInzeratov = new ArrayList<>();
            ArrayList<Kategoria> zoznamVozidiel = new ArrayList<>();
            zoznamInzeratov = autobazar.getZoznamInzeratov();  
            zoznamVozidiel = autobazar.getZoznamVozidiel();
            while(rs.next()){    
                Inzerat inzerat = new Inzerat();
                int idInzeratu = Integer.parseInt(rs.getString("id_inzeratu"));
                DateFormat format = new SimpleDateFormat("YYYY-MM-DD"); 
                Date datum = new Date(format.parse(rs.getString("datum")).getTime());  
                
                inzerat.setID(idInzeratu);               
                inzerat.setDatumVytvorenia(datum);
                inzerat.setPredajca(autobazar.najdiPredajcu(rs.getString("email")));
                inzerat.setStav(StavInzeratu.valueOf(rs.getString("stav")));
                     
                switch (getKategoriaInstance(idInzeratu, dbPath)) {
                    case 1:
                        {
                            Kategoria kategoria = getAutomobil(idInzeratu, dbPath);
                            inzerat.setKategoria(kategoria);
                            zoznamVozidiel.add(kategoria);
                            break;
                        }
                    case 2:
                        {
                            Kategoria kategoria = getMotocykel(idInzeratu, dbPath);
                            inzerat.setKategoria(kategoria);
                            zoznamVozidiel.add(kategoria);
                            break;
                        }
                    case 3:               
                        {
                            Kategoria kategoria = getNakladne(idInzeratu, dbPath);
                            inzerat.setKategoria(kategoria);
                            zoznamVozidiel.add(kategoria);
                            break;
                        }
                    case 4:
                        {
                            Kategoria kategoria = getAutobus(idInzeratu, dbPath);
                            inzerat.setKategoria(kategoria);
                            zoznamVozidiel.add(kategoria);
                            break;
                        }
                }
                zoznamInzeratov.add(inzerat);
            }                  
            autobazar.setZoznamInzeratov(zoznamInzeratov); 
            autobazar.setZoznamVozidiel(zoznamVozidiel);
            con.close();
            return autobazar;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
    /**
     *
     * @param idInzeratu
     * @param dbPath
     * @return
     */
    public static Automobil getAutomobil(int idInzeratu, String dbPath){
        try (Connection con = ConnectToDB.ConnectDB(dbPath);){           
            Statement state = con.createStatement();
            String sql = "SELECT * from Vozidlo JOIN Inzerat USING (id_vozidla) WHERE id_kategoria=1 and id_inzeratu='"+idInzeratu+"';";
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                String karoseria = rs.getString("karoseria");
                int pocetDveri = Integer.parseInt(rs.getString("pocetDveri"));
                int pocetSedadiel = Integer.parseInt(rs.getString("pocetSedadiel"));
                //Vozidlo
                int cena = Integer.parseInt(rs.getString("cena"));
                String model = rs.getString("model");
                int najazdeneKM = Integer.parseInt(rs.getString("pocetKm"));
                String palivo = rs.getString("palivo");
                int rokVyroby = Integer.parseInt(rs.getString("rokVyroby"));
                StavVozidla stav = StavVozidla.valueOf(rs.getString("stav_vozidla"));
                int vykon = Integer.parseInt(rs.getString("vykon"));
                String znacka = rs.getString("znacka");
                String popis = rs.getString("popis");
                Automobil auto = new Automobil(karoseria, pocetDveri, pocetSedadiel, cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
                con.close();
                return auto;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }       
        return null;
    }
    
    private static Motocykel getMotocykel(int idInzeratu, String dbPath) {
        try {
            Connection con = ConnectToDB.ConnectDB(dbPath);
            Statement state = con.createStatement();
            String sql = "SELECT * from Vozidlo JOIN Inzerat USING (id_vozidla) WHERE id_kategoria=2 and id_inzeratu='"+idInzeratu+"';";
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                //Vozidlo
                int cena = Integer.parseInt(rs.getString("cena"));
                String model = rs.getString("model");
                int najazdeneKM = Integer.parseInt(rs.getString("pocetKm"));
                String palivo = rs.getString("palivo");
                int rokVyroby = Integer.parseInt(rs.getString("rokVyroby"));
                StavVozidla stav = StavVozidla.valueOf(rs.getString("stav_vozidla"));
                int vykon = Integer.parseInt(rs.getString("vykon"));
                String znacka = rs.getString("znacka");
                String popis = rs.getString("popis");
                //Motoccykel
                String druh = rs.getString("karoseria");
                int objemValcov = Integer.parseInt(rs.getString("objemValcov"));
                Motocykel moto = new Motocykel(druh, objemValcov, cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
                con.close();
                return moto;
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);           
        }
        return null;
    }

    private static NakladneAuto getNakladne(int idInzeratu, String dbPath) {
         try (Connection con = ConnectToDB.ConnectDB(dbPath);){           
            Statement state = con.createStatement();
            String sql = "SELECT * from Vozidlo JOIN Inzerat USING (id_vozidla) WHERE id_kategoria=3 and id_inzeratu='"+idInzeratu+"';";
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int cena = Integer.parseInt(rs.getString("cena"));
                String model = rs.getString("model");
                int najazdeneKM = Integer.parseInt(rs.getString("pocetKm"));
                String palivo = rs.getString("palivo");
                int rokVyroby = Integer.parseInt(rs.getString("rokVyroby"));
                StavVozidla stav = StavVozidla.valueOf(rs.getString("stav_vozidla"));
                int vykon = Integer.parseInt(rs.getString("vykon"));
                String znacka = rs.getString("znacka");
                String popis = rs.getString("popis");
                //Nakladne
                String druh = rs.getString("karoseria");
                String normaEmisii = rs.getString("normaEmisii");
                int nosnost = Integer.parseInt(rs.getString("nosnost"));
                int hmotnost = Integer.parseInt(rs.getString("hmotnostVozidla"));
                NakladneAuto nakladne = new NakladneAuto(druh, hmotnost, normaEmisii, nosnost, cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
                con.close();
                return nakladne;
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);            
        }
         return null;
    }
    
    private static BatozinovyPriestor getBatozinovyPriestorPreAutobus(String bpString){
        BatozinovyPriestor bp = new BatozinovyPriestor();
        Pattern p1 = Pattern.compile("(Žiadny)");
        Pattern p2 = Pattern.compile("(Nosič lyží)");
        Pattern p3 = Pattern.compile("(Klasický)");
        Pattern p4 = Pattern.compile("(Vysoký)");
        Matcher m = p1.matcher(bpString);
        if(m.find()) bp.setZiadny(true);
        m = p2.matcher(bpString);
        if(m.find()) bp.setNosicLyzi(true);
        m = p3.matcher(bpString);
        if(m.find()) bp.setKlasicky(true);
        m = p4.matcher(bpString);
        if(m.find()) bp.setVysoky(true);
        
        return bp;
    }
    
    private static VlastnostiSedadiel getVlasnotiSedadielPreAutobus(String vsString){
        VlastnostiSedadiel vs = new VlastnostiSedadiel();
        Pattern p1 = Pattern.compile("(Štandart)");
        Pattern p2 = Pattern.compile("(Polohovateľné)");
        Pattern p3 = Pattern.compile("(Posuvné do uličky)");
        Pattern p4 = Pattern.compile("(Nastaviteľné)");
        Matcher m = p1.matcher(vsString);
        if(m.find()) vs.setStandart(true);
        m = p2.matcher(vsString);
        if(m.find()) vs.setPolohovatelne(true);
        m = p3.matcher(vsString);
        if(m.find()) vs.setPosuvne(true);
        m = p4.matcher(vsString);
        if(m.find()) vs.setNastavitelne(true);
        return vs;
    }

    private static Autobus getAutobus(int idInzeratu, String dbPath) {
         try(Connection con = ConnectToDB.ConnectDB(dbPath);) {           
            Statement state = con.createStatement();
            String sql = "SELECT * from Vozidlo JOIN Inzerat USING (id_vozidla) WHERE id_kategoria=4 and id_inzeratu='"+idInzeratu+"';";
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int cena = Integer.parseInt(rs.getString("cena"));
                String model = rs.getString("model");
                int najazdeneKM = Integer.parseInt(rs.getString("pocetKm"));
                String palivo = rs.getString("palivo");
                int rokVyroby = Integer.parseInt(rs.getString("rokVyroby"));
                StavVozidla stav = StavVozidla.valueOf(rs.getString("stav_vozidla"));
                int vykon = Integer.parseInt(rs.getString("vykon"));
                String znacka = rs.getString("znacka");
                String popis = rs.getString("popis");
                //autobus
                String druh = rs.getString("karoseria");
                String normaEmisii = rs.getString("normaEmisii");                            
                String bpString = rs.getString("batozPriestor");
                BatozinovyPriestor bp = getBatozinovyPriestorPreAutobus(bpString);
                String vsString = rs.getString("vlastnostiSedadiel");
                VlastnostiSedadiel vs = getVlasnotiSedadielPreAutobus(vsString);
                String nadstavba = rs.getString("nadstavba");
                int pocetMiest = Integer.parseInt(rs.getString("pocetSedadiel"));    
                Autobus autobus = new Autobus(druh, normaEmisii, bp, nadstavba, pocetMiest, vs, cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
                con.close();
                return autobus;
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);         
        }
         return null;
    }
}
