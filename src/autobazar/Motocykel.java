package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:52
 */
public class Motocykel extends Vozidlo implements Kategoria, Serializable{
	private String druh;
	private int objemValcov;

    /**
     * Konštruktor pre tvorbu objektu typu Motocykel.
     * @param druh
     * @param objemValcov
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
    public Motocykel(String druh, int objemValcov, int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        super(cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
        this.druh = druh;
        this.objemValcov = objemValcov;
    }
    
    /**
     * Getter pre druh motocykla.
     * @return 
     */
    public String getDruh() {
        return druh;
    }

    /**
     * Setter pre druh motocykla motocykla.
     * @param druh 
     */
    public void setDruh(String druh) {
        this.druh = druh;
    }

    /**
     * Getter pre objem valcov motocykla.
     * @return 
     */
    public int getObjemValcov() {
        return objemValcov;
    }

    /**
     * Setter pre objem valcov motocykla.
     * @param objemValcov 
     */
    public void setObjemValcov(int objemValcov) {
        this.objemValcov = objemValcov;
    }

    /**
     * Setter pre znacku motocykla.
     * @param znacka 
     */
    @Override
    public void setZnacka(String znacka) {
        super.setZnacka(znacka); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre znacku motocykla.
     * @return 
     */
    @Override
    public String getZnacka() {
        return super.getZnacka(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre vykon motocykla.
     * @param vykon 
     */
    @Override
    public void setVykon(int vykon) {
        super.setVykon(vykon); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre vykon motocykla.
     * @return 
     */
    @Override
    public int getVykon() {
        return super.getVykon(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Setter pre popis motocykla.
     * @param popis 
     */
    @Override
    public void setPopis(String popis) {
        super.setPopis(popis); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre popis motocykla.
     * @return 
     */
    @Override
    public String getPopis() {
        return super.getPopis(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre stav motocykla.
     * @param stav 
     */
    @Override
    public void setStav(StavVozidla stav) {
        super.setStav(stav); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre stav vozidla motocykla.
     * @return 
     */
    @Override
    public StavVozidla getStav() {
        return super.getStav(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre rok výroby motocykla.
     * @param rokVyroby 
     */
    @Override
    public void setRokVyroby(int rokVyroby) {
        super.setRokVyroby(rokVyroby); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre rok výroby motocykla.
     * @return 
     */
    @Override
    public int getRokVyroby() {
        return super.getRokVyroby(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre palivo motocykla.
     * @param palivo 
     */
    @Override
    public void setPalivo(String palivo) {
        super.setPalivo(palivo); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre palivo motocykla.
     * @return 
     */
    @Override
    public String getPalivo() {
        return super.getPalivo(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre najazdene kilometre motocykla.
     * @param najazdeneKM 
     */
    @Override
    public void setNajazdeneKM(int najazdeneKM) {
        super.setNajazdeneKM(najazdeneKM); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre najazdene kilometre motocykla.
     * @return 
     */
    @Override
    public int getNajazdeneKM() {
        return super.getNajazdeneKM(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre model motocykla.
     * @param model 
     */
    @Override
    public void setModel(String model) {
        super.setModel(model); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre model motocykla.
     * @return 
     */
    @Override
    public String getModel() {
        return super.getModel(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre cenu motocykla.
     * @param cena 
     */
    @Override
    public void setCena(int cena) {
        super.setCena(cena); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre cenu motocykla.
     * @return 
     */
    @Override
    public int getCena() {
        return super.getCena(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre kategoriu motocykla.
     * @return 
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
     * @return 
     */
    @Override
    public int celkovaHodnotaVozidiel() {
        return super.getCena();
    } 
    
    /**
     * Znaková reprezentácia objektu typu Motocykel.
     * @return 
     */
    @Override
    public String toString() {
        return "Motocykel{" + "druh=" + druh + ", objemValcov=" + objemValcov + ", " + super.toString() +'}';
    }

}