package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Trieda repzrezentujúca nákladné auto v autobazáre.
 * @author Cachovan Jakub
 * @version 1.0
 */
public class NakladneAuto extends Vozidlo implements Kategoria, Serializable{
        private String druh;
	private int hmotnostVozidla;
	private String normaEmisii;
	private int nosnost;

    /**
     * Konštruktor pre vytvorenie objektu typu NakladneAuto
     * @param druh - druh
     * @param hmotnostVozidla - hmotnost vozidla
     * @param normaEmisii - normaEmisii
     * @param nosnost  - nosnost
     * @param cena - cena
     * @param model - model
     * @param najazdeneKM - najazdene KM
     * @param palivo -  palivo
     * @param rokVyroby - rok vyroby
     * @param stav - stav
     * @param vykon  - vykon
     * @param znacka - znacka
     * @param popis - popis
     */
    public NakladneAuto(String druh, int hmotnostVozidla, String normaEmisii, int nosnost, int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        super(cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
        this.druh = druh;
        this.hmotnostVozidla = hmotnostVozidla;
        this.normaEmisii = normaEmisii;
        this.nosnost = nosnost;
    }
    
    /**
     * Getter pre druh nákladného auta.
     * @return druh nákladného auta.
     */
    public String getDruh() {
        return druh;
    }

    /**
     * Setter pre druh nákladného auta.
     * @param druh druh nákladného auta.
     */
    public void setDruh(String druh) {
        this.druh = druh;
    }
        
    /**
     * Getter pre hmotnost nákladného auta.
     * @return hmotnost nákladného auta.
     */
    public int getHmotnostVozidla() {
        return hmotnostVozidla;
    }

    /**
     * Setter pre hmotnost nákladného auta.
     * @param hmotnostVozidla hmotnost nákladného auta.
     */
    public void setHmotnostVozidla(int hmotnostVozidla) {
        this.hmotnostVozidla = hmotnostVozidla;
    }

    /**
     * Getter pre normu emisii nákladného auta.
     * @return norma emisii nákladného auta
     */
    public String getNormaEmisii() {
        return normaEmisii;
    }

    /**
     * Setter pre druh nákladného auta.
     * @param normaEmisii norma emisii nákladného auta
     */
    public void setNormaEmisii(String normaEmisii) {
        this.normaEmisii = normaEmisii;
    }

    /**
     * Getter pre nosnosť nákladného auta.
     * @return nosnosť nákladného auta
     */
    public int getNosnost() {
        return nosnost;
    }

    /**
     * Setter pre nosnost nákladného auta.
     * @param nosnost nosnosť nákladného auta
     */
    public void setNosnost(int nosnost) {
        this.nosnost = nosnost;
    }

    /**
     * Setter pre značku nákladného auta.
     * @param znacka značka nákladného auta
     */
    @Override
    public void setZnacka(String znacka) {
        super.setZnacka(znacka); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre značku nákladného auta.
     * @return značka nákladného auta
     */
    @Override
    public String getZnacka() {
        return super.getZnacka(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre výkon nákladného auta.
     * @param vykon výkon nákladného auta
     */
    @Override
    public void setVykon(int vykon) {
        super.setVykon(vykon); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre výkon nákladného auta.
     * @return výkon nákladného auta
     */
    @Override
    public int getVykon() {
        return super.getVykon(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Setter pre popis nákladného auta.
     * @param popis popis nákladného auta
     */
    @Override
    public void setPopis(String popis) {
        super.setPopis(popis); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre popis nákladného auta.
     * @return popis nákladného auta
     */
    @Override
    public String getPopis() {
        return super.getPopis(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre stav nákladného auta.
     * @param stav stav nákladného auta
     */
    @Override
    public void setStav(StavVozidla stav) {
        super.setStav(stav); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre stav nákladného auta.
     * @return stav nákladného auta
     */
    @Override
    public StavVozidla getStav() {
        return super.getStav(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre rok výroby nákladného auta.
     * @param rokVyroby rok výroby nákladného auta
     */
    @Override
    public void setRokVyroby(int rokVyroby) {
        super.setRokVyroby(rokVyroby); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre rok výroby nákladného auta.
     * @return rok výroby nákladného auta
     */
    @Override
    public int getRokVyroby() {
        return super.getRokVyroby(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre palivo nákladného auta.
     * @param palivo palivo nákladného auta
     */
    @Override
    public void setPalivo(String palivo) {
        super.setPalivo(palivo); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre palivo nákladného auta.
     * @return palivo nákladného auta
     */
    @Override
    public String getPalivo() {
        return super.getPalivo(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre najazdené kilometre nákladného auta.
     * @param najazdeneKM najazdené kilometre nákladného auta
     */
    @Override
    public void setNajazdeneKM(int najazdeneKM) {
        super.setNajazdeneKM(najazdeneKM); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre najazdené kilometre nákladného auta.
     * @return najazdené kilometre nákladného auta
     */
    @Override
    public int getNajazdeneKM() {
        return super.getNajazdeneKM(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre model nákladného auta.
     * @param model model nákladného auta
     */
    @Override
    public void setModel(String model) {
        super.setModel(model); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre model nákladného auta.
     * @return model nákladného auta
     */
    @Override
    public String getModel() {
        return super.getModel(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre cenu nákladného auta.
     * @param cena cena nákladného auta
     */
    @Override
    public void setCena(int cena) {
        super.setCena(cena); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Getter pre cenu nákladného auta.
     * @return cena nákladného auta
     */
    @Override
    public int getCena() {
        return super.getCena(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre kategoriu nakladneho vozidla.
     * 3-reprezentuje nákladné auto
     * @return kategoria nakladneho vozidla
     */
    @Override
    public int getKategoriaInt() {
        return 3;
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
     * @return cena vozidla
     */
    @Override
    public int celkovaHodnotaVozidiel() {
        return super.getCena();
    } 

    /**
     * Znaková reprezentácia objektu typu NakladneAuto.
     * @return String
     */
    @Override
    public String toString() {
        return "NakladneAuto{" + "druh=" + druh + ", hmotnostVozidla=" + hmotnostVozidla + ", normaEmisii=" + normaEmisii + ", nosnost=" + nosnost + ", " + super.toString() +'}';
    }

    

}