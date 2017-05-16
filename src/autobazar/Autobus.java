package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:51
 */
public class Autobus extends Vozidlo implements Kategoria, Serializable{

        private String druh; 
        private String normaEmisii;
	private BatozinovyPriestor batozinovyPriestor;
	private String nadstavba;
	private int pocetMiestNaSedenie;
	private VlastnostiSedadiel vlastnostiSedadiel;

        
    /**
     * Konštruktor pre vytvoreni objektu typu Autobus
     * @param druh
     * @param normaEmisii
     * @param batozinovyPriestor
     * @param nadstavba
     * @param pocetMiestNaSedenie
     * @param vlastnostiSedadiel
     * @param cena
     * @param model
     * @param najazdeneKM
     * @param palivo
     * @param rokVyroby
     * @param stav
     * @param vykon
     * @param znacka
     * @param popis 
     */
    public Autobus(String druh, String normaEmisii, BatozinovyPriestor batozinovyPriestor, String nadstavba, int pocetMiestNaSedenie, VlastnostiSedadiel vlastnostiSedadiel, int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        super(cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
        this.druh = druh;
        this.normaEmisii = normaEmisii;
        this.batozinovyPriestor = batozinovyPriestor;
        this.nadstavba = nadstavba;
        this.pocetMiestNaSedenie = pocetMiestNaSedenie;
        this.vlastnostiSedadiel = vlastnostiSedadiel;
    }
    
    
    /**
     * Getter pre druh vozidla.
     * @return 
     */
    public String getDruh() {
        return druh;
    }

    /**
     * Setter pre druh vozidla.
     * @param druh 
     */
    public void setDruh(String druh) {
        this.druh = druh;
    }

    /**
     * Getter pre normu emisii vozidla.
     * @return 
     */
    public String getNormaEmisii() {
        return normaEmisii;
    }

    /**
     * Setter pre normu emisií vozidla.
     * @param normaEmisii 
     */
    public void setNormaEmisii(String normaEmisii) {
        this.normaEmisii = normaEmisii;
    }

    /**
     * Getter pre objekt typu BatozinovyPriestor.
     * @return 
     */
    public BatozinovyPriestor getBatozinovyPriestor() {
        return batozinovyPriestor;
    }

    /**
     * Setter pre objekt typu BatozinovyPriestor.
     * @param batozinovyPriestor 
     */
    public void setBatozinovyPriestor(BatozinovyPriestor batozinovyPriestor) {
        this.batozinovyPriestor = batozinovyPriestor;
    }

    /**
     * Getter pre nadstavbu vozidla.
     * @return 
     */
    public String getNadstavba() {
        return nadstavba;
    }

    /**
     * Setter pre nadstavbu vozidla.
     * @param nadstavba 
     */
    public void setNadstavba(String nadstavba) {
        this.nadstavba = nadstavba;
    }

    /**
     * Getter pre počet miest na sedenie.
     * @return integer
     */
    public int getPocetMiestNaSedenie() {
        return pocetMiestNaSedenie;
    }

    /**
     * Setter pre počet miest na sedenie.
     * @param pocetMiestNaSedenie 
     */
    public void setPocetMiestNaSedenie(int pocetMiestNaSedenie) {
        this.pocetMiestNaSedenie = pocetMiestNaSedenie;
    }

    /**
     * Getter pre objekt typu VlastnostiSedadiel.
     * @return 
     */
    public VlastnostiSedadiel getVlastnostiSedadiel() {
        return vlastnostiSedadiel;
    }

    /**
     * Setter pre objekt typu VlastnostiSedadiel.
     * @param vlastnostiSedadiel 
     */
    public void setVlastnostiSedadiel(VlastnostiSedadiel vlastnostiSedadiel) {
        this.vlastnostiSedadiel = vlastnostiSedadiel;
    }

    /**
     * Setter pre značku autobusu.
     * @param znacka 
     */
    @Override
    public void setZnacka(String znacka) {
        super.setZnacka(znacka); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre značku autobusu.
     * @return 
     */
    @Override
    public String getZnacka() {
        return super.getZnacka(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre výkon autobusu.
     * @param vykon 
     */
    @Override
    public void setVykon(int vykon) {
        super.setVykon(vykon); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre výkon autobusu.
     * @return 
     */
    @Override
    public int getVykon() {
        return super.getVykon(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre popis autobusu.
     * @param popis 
     */
    @Override
    public void setPopis(String popis) {
        super.setPopis(popis); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre popis autobusu.
     * @return 
     */
    @Override
    public String getPopis() {
        return super.getPopis();
    }

    /**
     * Setter pre stav autobusu.
     * @param stav 
     */
    @Override
    public void setStav(StavVozidla stav) {
        super.setStav(stav);
    }

    /**
     * Getter pre stav autobusu.
     * @return 
     */
    @Override
    public StavVozidla getStav() {
        return super.getStav();
    }

    /**
     * Setter pre rok výroby autobusu.
     * @param rokVyroby 
     */
    @Override
    public void setRokVyroby(int rokVyroby) {
        super.setRokVyroby(rokVyroby); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre rok výroby autobusu.
     * @return 
     */
    @Override
    public int getRokVyroby() {
        return super.getRokVyroby(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre palivo autobusu.
     * @param palivo 
     */
    @Override
    public void setPalivo(String palivo) {
        super.setPalivo(palivo); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre palivo autobusu.
     * @return 
     */
    @Override
    public String getPalivo() {
        return super.getPalivo(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre najazdené kilometre autobusu.
     * @param najazdeneKM 
     */
    @Override
    public void setNajazdeneKM(int najazdeneKM) {
        super.setNajazdeneKM(najazdeneKM); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre najazdené kilometre autobusu.
     * @return 
     */
    @Override
    public int getNajazdeneKM() {
        return super.getNajazdeneKM(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre model autobusu.
     * @param model 
     */
    @Override
    public void setModel(String model) {
        super.setModel(model); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre model autobusu.
     * @return 
     */
    @Override
    public String getModel() {
        return super.getModel(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre cenu autobusu.
     * @param cena 
     */
    @Override
    public void setCena(int cena) {
        super.setCena(cena); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre cenu autobusu.
     * @return 
     */
    @Override
    public int getCena() {
        return super.getCena(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre kategoriu autobusu.
     * 4-> reprezentuje Autobus
     * @return 
     */
    @Override
    public int getKategoriaInt() {
        return 4;
    }
    
    /**
     * Metóda pre zistenie zhody kľúčového slova v znakovej reprezentácií objektu (toString).
     * @param keyWord - kľúčové slovo
     * @return true/false
     */
    @Override
    public boolean obsahujeKlucoveSlovo(String keyWord) {      
        Pattern p = Pattern.compile("("+keyWord+")");
        Matcher m = p.matcher(this.toString());
        return m.find();       
    }
    
    /**
     * Metóda potrebná pre zistenie ceny vozidla.
     * @return 
     */
    @Override
    public int celkovaHodnotaVozidiel() {
        return super.getCena();
    }   

    /**
     * Znaková reprezentácia objektu typu Autobus.
     * @return 
     */
    @Override
    public String toString() {
        return "Autobus{" + "druh=" + druh + ", normaEmisii=" + normaEmisii + ", batozinovyPriestor=" + batozinovyPriestor + ", nadstavba=" + nadstavba + ", pocetMiestNaSedenie=" + pocetMiestNaSedenie + ", vlastnostiSedadiel=" + vlastnostiSedadiel + ", " + super.toString() +'}';
    }


    
	
}