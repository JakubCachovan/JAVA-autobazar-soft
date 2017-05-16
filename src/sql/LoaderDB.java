package sql;

import autobazar.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Trieda pre načítanie dát pre program z databázy.
 * @author Jakub Cachovan
 */
public class LoaderDB {
    
    /**
     * Metóda zistuje id vozidla, ktorý je priradený inzerátu.
     * @param idInzeratu - id inzeratu
     * @param dbPath - cesta k databáze
     * @return id vozidla
     */
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
    
    /**
     * Zistenie kategorie vozidla podla id inzeratu.
     * Metóda indentifikuje kategóriu a vrátí vráti indentifikátor kategorie.
     * 1- Automobil, 2- Motocykel, 3- Nakladne, 4- Autobus
     * 
     * @param idInzeratu - id inzeratu
     * @param dbPath  - cesta do DB
     * @return integer
     */
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
    
    /**
     * Načíta všetky dáta pre autobazár.
     * @param dbPath - cesta k datbaze
     * @return objekt typu Autobazar
     */
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
     * Statická metóda pre načítanie objektu typu Automobil z DB.
     * @param idInzeratu - id inzerátu
     * @param dbPath - cesta k datbaze
     * @return objekt typu Automobil
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
    
    /**
     * Statická metóda pre načítanie objektu typu Motocykel z DB.
     * @param idInzeratu - id inzerátu
     * @param dbPath - cesta k datbaze
     * @return  objekt typu Motocykel
     */
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

    /**
     * Statická metóda pre načítanie objektu typu NakldneAuto z DB.
     * @param idInzeratu - id inzerátu
     * @param dbPath - cesta k datbaze
     * @return  objekt typu NakladneAuto
     */
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
    
    /**
     * Statická metóda pre zistenie zhody jednotlivých časti výbavy pre batožinový priestor a nastavenie objektu typu BatozinovyPriestor na požadované hodnoty.
     * @param bpString - retazec obsahujuci vybavu batozinoveho priestoru oddelený čiarkami
     * @return  objekt typu BatozinovyPriestor
     */
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
    
    /**
     * Statická metóda pre zistenie zhody jednotlivých časti výbavy pre vlastnosti sedadiel a nastavenie objektu typu VlastnostiSedadiel na požadované hodnoty.
     * @param vsString - retazec obsahujuci vybavu vlastností sedadiel oddelený čiarkami
     * @return  objekt typu VlastnostiSedadiel
     */
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

    /**
     * Statická metóda pre načítanie objektu typu Autobus z DB.
     * @param idInzeratu - id inzerátu
     * @param dbPath - cesta k datbaze
     * @return  objekt typu Autobus
     */
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
