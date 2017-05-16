package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Trieda reprezentujúca Motocykel.
 * @author Cachovan Jakub
 * @version 1.0
 */
public class Motocykel extends Vozidlo implements Kategoria, Serializable{
	private String druh;
	private int objemValcov;

    /**
     * Konštruktor pre tvorbu objektu typu Motocykel.
     * @param druh - druh motocyklu
     * @param objemValcov - objem válcov motocyklu
     * @param cena - cena motocyklu
     * @param model - model motocyklu
     * @param najazdeneKM - najazdene kilometre motocyklu
     * @param palivo - palivom motocyklu
     * @param rokVyroby - rokVyroby motocyklu
     * @param stav - stav motocyklu
     * @param vykon - vykon motocyklu
     * @param znacka - znacka motocyklu
     * @param popis - popis motocyklu
     */
    public Motocykel(String druh, int objemValcov, int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        super(cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
        this.druh = druh;
        this.objemValcov = objemValcov;
    }
    
    /**
     * Getter pre druh motocykla.
     * @return druh motocykla.
     */
    public String getDruh() {
        return druh;
    }

    /**
     * Setter pre druh motocykla motocykla.
     * @param druh druh motocykla.
     */
    public void setDruh(String druh) {
        this.druh = druh;
    }

    /**
     * Getter pre objem valcov motocykla.
     * @return objem valcov motocykla.
     */
    public int getObjemValcov() {
        return objemValcov;
    }

    /**
     * Setter pre objem valcov motocykla.
     * @param objemValcov objem valcov motocykla.
     */
    public void setObjemValcov(int objemValcov) {
        this.objemValcov = objemValcov;
    }

    /**
     * Setter pre znacku motocykla.
     * @param znacka znacku motocykla.
     */
    @Override
    public void setZnacka(String znacka) {
        super.setZnacka(znacka); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre znacku motocykla.
     * @return znacku motocykla.
     */
    @Override
    public String getZnacka() {
        return super.getZnacka(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre vykon motocykla.
     * @param vykon vykon motocykla.
     */
    @Override
    public void setVykon(int vykon) {
        super.setVykon(vykon); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre vykon motocykla.
     * @return vykon motocykla.
     */
    @Override
    public int getVykon() {
        return super.getVykon(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Setter pre popis motocykla.
     * @param popis  popis motocykla.
     */
    @Override
    public void setPopis(String popis) {
        super.setPopis(popis); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre popis motocykla.
     * @return  popis motocykla.
     */
    @Override
    public String getPopis() {
        return super.getPopis(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre stav motocykla.
     * @param stav stav motocykla.
     */
    @Override
    public void setStav(StavVozidla stav) {
        super.setStav(stav); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre stav vozidla motocykla.
     * @return stav motocykla.
     */
    @Override
    public StavVozidla getStav() {
        return super.getStav(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre rok výroby motocykla.
     * @param rokVyroby rok výroby motocykla.
     */
    @Override
    public void setRokVyroby(int rokVyroby) {
        super.setRokVyroby(rokVyroby); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre rok výroby motocykla.
     * @return rok výroby motocykla.
     */
    @Override
    public int getRokVyroby() {
        return super.getRokVyroby(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre palivo motocykla.
     * @param palivo palivo motocykla.
     */
    @Override
    public void setPalivo(String palivo) {
        super.setPalivo(palivo); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre palivo motocykla.
     * @return palivo motocykla.
     */
    @Override
    public String getPalivo() {
        return super.getPalivo(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre najazdene kilometre motocykla.
     * @param najazdeneKM najazdene kilometre motocykla.
     */
    @Override
    public void setNajazdeneKM(int najazdeneKM) {
        super.setNajazdeneKM(najazdeneKM); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre najazdene kilometre motocykla.
     * @return najazdene kilometre motocykla.
     */
    @Override
    public int getNajazdeneKM() {
        return super.getNajazdeneKM(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre model motocykla.
     * @param model model motocykla.
     */
    @Override
    public void setModel(String model) {
        super.setModel(model); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre model motocykla.
     * @return model motocykla.
     */
    @Override
    public String getModel() {
        return super.getModel(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre cenu motocykla.
     * @param cena cena motocykla.
     */
    @Override
    public void setCena(int cena) {
        super.setCena(cena); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre cenu motocykla.
     * @return cena motocykla.
     */
    @Override
    public int getCena() {
        return super.getCena(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre kategoriu motocykla.
     * @return kategoria motocykla.
     */
    @Override
    public int getKategoriaInt() {
        return 2;
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
     * Znaková reprezentácia objektu typu Motocykel.
     * @return String
     */
    @Override
    public String toString() {
        return "Motocykel{" + "druh=" + druh + ", objemValcov=" + objemValcov + ", " + super.toString() +'}';
    }

}