package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Trieda reprezentujúca Autobus.
 * @author Cachovan Jakub
 * @version 1.0
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
     * @param druh - druh autobusu
     * @param normaEmisii - norma emisii autobusu
     * @param batozinovyPriestor - batozinovy priestor autobusu
     * @param nadstavba - nadstavba autobusu
     * @param pocetMiestNaSedenie - pocet miet na sedenie
     * @param vlastnostiSedadiel - vlastnosti sedadiel
     * @param cena - cena
     * @param model - model
     * @param najazdeneKM - najazdene kilometre
     * @param palivo - palivo
     * @param rokVyroby - rok výroby
     * @param stav - stav vozidla
     * @param vykon - vykon
     * @param znacka - znacka
     * @param popis  - popis
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
     * @return druh autobusu
     */
    public String getDruh() {
        return druh;
    }

    /**
     * Setter pre druh vozidla.
     * @param druh - druh autobusu
     */
    public void setDruh(String druh) {
        this.druh = druh;
    }

    /**
     * Getter pre normu emisii vozidla.
     * @return norma emisii
     */
    public String getNormaEmisii() {
        return normaEmisii;
    }

    /**
     * Setter pre normu emisií vozidla.
     * @param normaEmisii - norma emisii
     */
    public void setNormaEmisii(String normaEmisii) {
        this.normaEmisii = normaEmisii;
    }

    /**
     * Getter pre objekt typu BatozinovyPriestor.
     * @return - batozinovy priestor
     */
    public BatozinovyPriestor getBatozinovyPriestor() {
        return batozinovyPriestor;
    }

    /**
     * Setter pre objekt typu BatozinovyPriestor.
     * @param batozinovyPriestor  - batožinový priestor
     */
    public void setBatozinovyPriestor(BatozinovyPriestor batozinovyPriestor) {
        this.batozinovyPriestor = batozinovyPriestor;
    }

    /**
     * Getter pre nadstavbu vozidla.
     * @return nadstavba autobusu
     */
    public String getNadstavba() {
        return nadstavba;
    }

    /**
     * Setter pre nadstavbu vozidla.
     * @param nadstavba - nadstavba autobusu
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
     * @param pocetMiestNaSedenie - počet miest na sedenie
     */
    public void setPocetMiestNaSedenie(int pocetMiestNaSedenie) {
        this.pocetMiestNaSedenie = pocetMiestNaSedenie;
    }

    /**
     * Getter pre objekt typu VlastnostiSedadiel.
     * @return objekt typu VlastnostiSedadiel
     */
    public VlastnostiSedadiel getVlastnostiSedadiel() {
        return vlastnostiSedadiel;
    }

    /**
     * Setter pre objekt typu VlastnostiSedadiel.
     * @param vlastnostiSedadiel - objekt typu VlastnostiSedadiel
     */
    public void setVlastnostiSedadiel(VlastnostiSedadiel vlastnostiSedadiel) {
        this.vlastnostiSedadiel = vlastnostiSedadiel;
    }

    /**
     * Setter pre značku autobusu.
     * @param znacka - značka autobusu
     */
    @Override
    public void setZnacka(String znacka) {
        super.setZnacka(znacka); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre značku autobusu.
     * @return značka autobusu
     */
    @Override
    public String getZnacka() {
        return super.getZnacka(); 
    }

    /**
     * Setter pre výkon autobusu.
     * @param vykon výkon autobusu v kW
     */
    @Override
    public void setVykon(int vykon) {
        super.setVykon(vykon); 
    }

    /**
     * Getter pre výkon autobusu.
     * @return výkon autobusu v kW
     */
    @Override
    public int getVykon() {
        return super.getVykon(); 
    }

    /**
     * Setter pre popis autobusu.
     * @param popis - popis autobusu
     */
    @Override
    public void setPopis(String popis) {
        super.setPopis(popis); 
    }

    /**
     * Getter pre popis autobusu.
     * @return popis autobusu
     */
    @Override
    public String getPopis() {
        return super.getPopis();
    }

    /**
     * Setter pre stav autobusu.
     * @param stav - stav autobusu, objekt typu StavVozidla
     */
    @Override
    public void setStav(StavVozidla stav) {
        super.setStav(stav);
    }

    /**
     * Getter pre stav autobusu.
     * @return objekt typu StavVozidla
     */
    @Override
    public StavVozidla getStav() {
        return super.getStav();
    }

    /**
     * Setter pre rok výroby autobusu.
     * @param rokVyroby - rok vyroby autobusu
     */
    @Override
    public void setRokVyroby(int rokVyroby) {
        super.setRokVyroby(rokVyroby); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre rok výroby autobusu.
     * @return rok vyroby autobusu
     */
    @Override
    public int getRokVyroby() {
        return super.getRokVyroby(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre palivo autobusu.
     * @param palivo palivo autobusu
     */
    @Override
    public void setPalivo(String palivo) {
        super.setPalivo(palivo); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre palivo autobusu.
     * @return palivo autobusu.
     */
    @Override
    public String getPalivo() {
        return super.getPalivo(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre najazdené kilometre autobusu.
     * @param najazdeneKM najazdené kilometre autobusu.
     */
    @Override
    public void setNajazdeneKM(int najazdeneKM) {
        super.setNajazdeneKM(najazdeneKM); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre najazdené kilometre autobusu.
     * @return najazdené kilometre autobusu.
     */
    @Override
    public int getNajazdeneKM() {
        return super.getNajazdeneKM(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre model autobusu.
     * @param model model autobusu.
     */
    @Override
    public void setModel(String model) {
        super.setModel(model); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre model autobusu.
     * @return model autobusu.
     */
    @Override
    public String getModel() {
        return super.getModel(); 
    }

    /**
     * Setter pre cenu autobusu.
     * @param cena cenu autobusu.
     */
    @Override
    public void setCena(int cena) {
        super.setCena(cena); 
    }

    /**
     * Getter pre cenu autobusu.
     * @return cenu autobusu.
     */
    @Override
    public int getCena() {
        return super.getCena(); 
    }

    /**
     * Getter pre kategoriu autobusu.
     * 4 - reprezentuje Autobus
     * @return kategoriu autobusu.
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
     * @return cena vozidla.
     */
    @Override
    public int celkovaHodnotaVozidiel() {
        return super.getCena();
    }   

    /**
     * Znaková reprezentácia objektu typu Autobus.
     * @return String
     */
    @Override
    public String toString() {
        return "Autobus{" + "druh=" + druh + ", normaEmisii=" + normaEmisii + ", batozinovyPriestor=" + batozinovyPriestor + ", nadstavba=" + nadstavba + ", pocetMiestNaSedenie=" + pocetMiestNaSedenie + ", vlastnostiSedadiel=" + vlastnostiSedadiel + ", " + super.toString() +'}';
    }


    
	
}