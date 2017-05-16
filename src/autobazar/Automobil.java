package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:52
 */
public class Automobil extends Vozidlo implements Kategoria, Serializable{

	private String karoseria;
	private int pocetDveri;
	private int pocetSedadiel;

    /**
     * Konštruktor pre vytvorenie objektu typu Automobil
     * @param karoseria
     * @param pocetDveri
     * @param pocetSedadiel
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
    public Automobil(String karoseria, int pocetDveri, int pocetSedadiel, int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        super(cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
        this.karoseria = karoseria;
        this.pocetDveri = pocetDveri;
        this.pocetSedadiel = pocetSedadiel;
    }

    /**
     * Getter pre karoseriu automobilu.
     * @return 
     */
    public String getKaroseria() {
        return karoseria;
    }
    /**
     * Setter pre karosériu automobilu.
     * @param karoseria 
     */
    public void setKaroseria(String karoseria) {
        this.karoseria = karoseria;
    }

    /**
     * Getter pre počet dverí.
     * @return 
     */
    public int getPocetDveri() {
        return pocetDveri;
    }

    /**
     * Setter pre počet dverí automobilu.
     * @param pocetDveri 
     */
    public void setPocetDveri(int pocetDveri) {
        this.pocetDveri = pocetDveri;
    }

    /**
     * Getter pre počet sedadiel automobilu.
     * @return 
     */
    public int getPocetSedadiel() {
        return pocetSedadiel;
    }

    /**
     * Setter pre počet sedadiel.
     * @param pocetSedadiel 
     */
    public void setPocetSedadiel(int pocetSedadiel) {
        this.pocetSedadiel = pocetSedadiel;
    }

    /**
     * Setter pre značku automobilu.
     * @param znacka 
     */
    @Override
    public void setZnacka(String znacka) {
        super.setZnacka(znacka); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre značku automobilu.
     * @return 
     */
    @Override
    public String getZnacka() {
        return super.getZnacka(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre výkon automobilu.
     * @param vykon 
     */
    @Override
    public void setVykon(int vykon) {
        super.setVykon(vykon); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre výkon automobilu.
     * @return 
     */
    @Override
    public int getVykon() {
        return super.getVykon(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Setter pre stav automobilu.
     * @param stav 
     */
    @Override
    public void setStav(StavVozidla stav) {
        super.setStav(stav); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre stav automobilu.
     * @return 
     */
    @Override
    public StavVozidla getStav() {
        return super.getStav(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Setter pre popis automobilu. 
     * @param popis 
     */
    @Override
    public void setPopis(String popis) {
        super.setPopis(popis); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre popis automobilu.
     * @return 
     */
    @Override
    public String getPopis() {
        return super.getPopis(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre rok výroby automobilu.
     * @param rokVyroby 
     */
    @Override
    public void setRokVyroby(int rokVyroby) {
        super.setRokVyroby(rokVyroby);
    }

    /**
     * Getter pre rok výroby automobilu.
     * @return 
     */
    @Override
    public int getRokVyroby() {
        return super.getRokVyroby();
    }

    /**
     * Setter pre palivo automobilu.
     * @param palivo 
     */
    @Override
    public void setPalivo(String palivo) {
        super.setPalivo(palivo); 
    }

    /**
     * Getter pre palivo automobilu.
     * @return 
     */
    @Override
    public String getPalivo() {
        return super.getPalivo(); 
    }
    
    /**
     * Setter pre najazdené kilometre automobilu.
     * @param najazdeneKM 
     */
    @Override
    public void setNajazdeneKM(int najazdeneKM) {
        super.setNajazdeneKM(najazdeneKM);
    }

    /**
     * Getter pre najazdené kilometre automobilu.
     * @return 
     */
    @Override
    public int getNajazdeneKM() {
        return super.getNajazdeneKM();
    }

    /**
     * Setter pre model automobilu.
     * @param model 
     */
    @Override
    public void setModel(String model) {
        super.setModel(model); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre model automobilu.
     * @return 
     */
    @Override
    public String getModel() {
        return super.getModel(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre cenu automobilu.
     * @param cena 
     */
    @Override
    public void setCena(int cena) {
        super.setCena(cena); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre kategoriu automobilu.
     * @return 
     */
    @Override
    public int getKategoriaInt() {
        return 1;
    }
    
    /**
     * Getter pre cenu automobilu.
     * @return 
     */
    @Override
    public int getCena() {
        return super.getCena(); //To change body of generated methods, choose Tools | Templates.
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
     * Znaková reprezentácia objektu typu Automobil.
     * @return 
     */
    @Override
    public String toString() {
        return "Automobil{" + "karoseria=" + karoseria + ", pocetDveri=" + pocetDveri + ", pocetSedadiel=" + pocetSedadiel + ", " + super.toString() + '}';
    }

    
	

}