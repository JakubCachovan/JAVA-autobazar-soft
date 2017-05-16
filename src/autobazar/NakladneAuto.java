package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:52
 */
public class NakladneAuto extends Vozidlo implements Kategoria, Serializable{
        private String druh;
	private int hmotnostVozidla;
	private String normaEmisii;
	private int nosnost;

    /**
     * 
     * @param druh
     * @param hmotnostVozidla
     * @param normaEmisii
     * @param nosnost
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
    public NakladneAuto(String druh, int hmotnostVozidla, String normaEmisii, int nosnost, int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        super(cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
        this.druh = druh;
        this.hmotnostVozidla = hmotnostVozidla;
        this.normaEmisii = normaEmisii;
        this.nosnost = nosnost;
    }
    
    /**
     * Getter pre druh nákladného auta.
     * @return 
     */
    public String getDruh() {
        return druh;
    }

    /**
     * Setter pre druh nákladného auta.
     * @param druh 
     */
    public void setDruh(String druh) {
        this.druh = druh;
    }
        
    /**
     * Getter pre hmotnost nákladného auta.
     * @return 
     */
    public int getHmotnostVozidla() {
        return hmotnostVozidla;
    }

    /**
     * Setter pre hmotnost nákladného auta.
     * @param hmotnostVozidla 
     */
    public void setHmotnostVozidla(int hmotnostVozidla) {
        this.hmotnostVozidla = hmotnostVozidla;
    }

    /**
     * Getter pre normu emisii nákladného auta.
     * @return 
     */
    public String getNormaEmisii() {
        return normaEmisii;
    }

    /**
     * Setter pre druh nákladného auta.
     * @param normaEmisii 
     */
    public void setNormaEmisii(String normaEmisii) {
        this.normaEmisii = normaEmisii;
    }

    /**
     * Getter pre nosnosť nákladného auta.
     * @return 
     */
    public int getNosnost() {
        return nosnost;
    }

    /**
     * Setter pre nosnost nákladného auta.
     * @param nosnost 
     */
    public void setNosnost(int nosnost) {
        this.nosnost = nosnost;
    }

    /**
     * Setter pre značku nákladného auta.
     * @param znacka 
     */
    @Override
    public void setZnacka(String znacka) {
        super.setZnacka(znacka); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre značku nákladného auta.
     * @return 
     */
    @Override
    public String getZnacka() {
        return super.getZnacka(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre výkon nákladného auta.
     * @param vykon 
     */
    @Override
    public void setVykon(int vykon) {
        super.setVykon(vykon); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre výkon nákladného auta.
     * @return 
     */
    @Override
    public int getVykon() {
        return super.getVykon(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Setter pre popis nákladného auta.
     * @param popis 
     */
    @Override
    public void setPopis(String popis) {
        super.setPopis(popis); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre popis nákladného auta.
     * @return 
     */
    @Override
    public String getPopis() {
        return super.getPopis(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre stav nákladného auta.
     * @param stav 
     */
    @Override
    public void setStav(StavVozidla stav) {
        super.setStav(stav); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre stav nákladného auta.
     * @return 
     */
    @Override
    public StavVozidla getStav() {
        return super.getStav(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre rok výroby nákladného auta.
     * @param rokVyroby 
     */
    @Override
    public void setRokVyroby(int rokVyroby) {
        super.setRokVyroby(rokVyroby); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre rok výroby nákladného auta.
     * @return 
     */
    @Override
    public int getRokVyroby() {
        return super.getRokVyroby(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre palivo nákladného auta.
     * @param palivo 
     */
    @Override
    public void setPalivo(String palivo) {
        super.setPalivo(palivo); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre palivo nákladného auta.
     * @return 
     */
    @Override
    public String getPalivo() {
        return super.getPalivo(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre najazdené kilometre nákladného auta.
     * @param najazdeneKM 
     */
    @Override
    public void setNajazdeneKM(int najazdeneKM) {
        super.setNajazdeneKM(najazdeneKM); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre najazdené kilometre nákladného auta.
     * @return 
     */
    @Override
    public int getNajazdeneKM() {
        return super.getNajazdeneKM(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre model nákladného auta.
     * @param model 
     */
    @Override
    public void setModel(String model) {
        super.setModel(model); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre model nákladného auta.
     * @return 
     */
    @Override
    public String getModel() {
        return super.getModel(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre cenu nákladného auta.
     * @param cena 
     */
    @Override
    public void setCena(int cena) {
        super.setCena(cena); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Getter pre cenu nákladného auta.
     * @return 
     */
    @Override
    public int getCena() {
        return super.getCena(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre kategoriu nakladneho vozidla.
     * 3-> reprezentuje nákladné auto
     * @return 
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
     * @return 
     */
    @Override
    public int celkovaHodnotaVozidiel() {
        return super.getCena();
    } 

    /**
     * Znaková reprezentácia objektu typu NakladneAuto.
     * @return 
     */
    @Override
    public String toString() {
        return "NakladneAuto{" + "druh=" + druh + ", hmotnostVozidla=" + hmotnostVozidla + ", normaEmisii=" + normaEmisii + ", nosnost=" + nosnost + ", " + super.toString() +'}';
    }

    

}