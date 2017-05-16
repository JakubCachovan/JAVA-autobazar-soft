package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Trieda reprezentujúca Automobil.
 * @author Cachovan Jakub
 * @version 1.0
 */
public class Automobil extends Vozidlo implements Kategoria, Serializable{

	private String karoseria;
	private int pocetDveri;
	private int pocetSedadiel;

    /**
     * Konštruktor pre vytvorenie objektu typu Automobil
     * @param karoseria karoséria vozidla
     * @param pocetDveri pocet dveri 
     * @param pocetSedadiel pocet sedadiel
     * @param cena cena
     * @param model model
     * @param najazdeneKM najazdene KM
     * @param palivo palivo
     * @param rokVyroby rok vyroby
     * @param stav stav
     * @param vykon vykon
     * @param znacka znacka
     * @param popis popis
     */
    public Automobil(String karoseria, int pocetDveri, int pocetSedadiel, int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        super(cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
        this.karoseria = karoseria;
        this.pocetDveri = pocetDveri;
        this.pocetSedadiel = pocetSedadiel;
    }

    /**
     * Getter pre karoseriu automobilu.
     * @return karoseria automobilu.
     */
    public String getKaroseria() {
        return karoseria;
    }
    /**
     * Setter pre karosériu automobilu.
     * @param karoseria karoseria automobilu.
     */
    public void setKaroseria(String karoseria) {
        this.karoseria = karoseria;
    }

    /**
     * Getter pre počet dverí.
     * @return počet dverí.
     */
    public int getPocetDveri() {
        return pocetDveri;
    }

    /**
     * Setter pre počet dverí automobilu.
     * @param pocetDveri počet dverí.
     */
    public void setPocetDveri(int pocetDveri) {
        this.pocetDveri = pocetDveri;
    }

    /**
     * Getter pre počet sedadiel automobilu.
     * @return počet sedadiel automobilu.
     */
    public int getPocetSedadiel() {
        return pocetSedadiel;
    }

    /**
     * Setter pre počet sedadiel.
     * @param pocetSedadiel počet sedadiel automobilu.
     */
    public void setPocetSedadiel(int pocetSedadiel) {
        this.pocetSedadiel = pocetSedadiel;
    }

    /**
     * Setter pre značku automobilu.
     * @param znacka značka automobilu.
     */
    @Override
    public void setZnacka(String znacka) {
        super.setZnacka(znacka); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre značku automobilu.
     * @return značka automobilu.
     */
    @Override
    public String getZnacka() {
        return super.getZnacka(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre výkon automobilu.
     * @param vykon výkon automobilu.
     */
    @Override
    public void setVykon(int vykon) {
        super.setVykon(vykon); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre výkon automobilu.
     * @return výkon automobilu.
     */
    @Override
    public int getVykon() {
        return super.getVykon(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Setter pre stav automobilu.
     * @param stav stav automobilu.
     */
    @Override
    public void setStav(StavVozidla stav) {
        super.setStav(stav); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre stav automobilu.
     * @return stav automobilu.
     */
    @Override
    public StavVozidla getStav() {
        return super.getStav(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Setter pre popis automobilu. 
     * @param popis popis automobilu. 
     */
    @Override
    public void setPopis(String popis) {
        super.setPopis(popis); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter pre popis automobilu.
     * @return popis automobilu. 
     */
    @Override
    public String getPopis() {
        return super.getPopis(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Setter pre rok výroby automobilu.
     * @param rokVyroby rok výroby automobilu.
     */
    @Override
    public void setRokVyroby(int rokVyroby) {
        super.setRokVyroby(rokVyroby);
    }

    /**
     * Getter pre rok výroby automobilu.
     * @return rok výroby automobilu.
     */
    @Override
    public int getRokVyroby() {
        return super.getRokVyroby();
    }

    /**
     * Setter pre palivo automobilu.
     * @param palivo palivo automobilu. 
     */
    @Override
    public void setPalivo(String palivo) {
        super.setPalivo(palivo); 
    }

    /**
     * Getter pre palivo automobilu.
     * @return palivo automobilu.
     */
    @Override
    public String getPalivo() {
        return super.getPalivo(); 
    }
    
    /**
     * Setter pre najazdené kilometre automobilu.
     * @param najazdeneKM najazdené kilometre automobilu.
     */
    @Override
    public void setNajazdeneKM(int najazdeneKM) {
        super.setNajazdeneKM(najazdeneKM);
    }

    /**
     * Getter pre najazdené kilometre automobilu.
     * @return najazdené kilometre automobilu.
     */
    @Override
    public int getNajazdeneKM() {
        return super.getNajazdeneKM();
    }

    /**
     * Setter pre model automobilu.
     * @param model model automobilu.
     */
    @Override
    public void setModel(String model) {
        super.setModel(model); 
    }

    /**
     * Getter pre model automobilu.
     * @return model automobilu.
     */
    @Override
    public String getModel() {
        return super.getModel(); 
    }

    /**
     * Setter pre cenu automobilu.
     * @param cena cena automobilu
     */
    @Override
    public void setCena(int cena) {
        super.setCena(cena); 
    }
    
    /**
     * Getter pre cenu automobilu.
     * @return cenu automobilu
     */
    @Override
    public int getCena() {
        return super.getCena();
    }

    /**
     * Getter pre kategoriu automobilu.
     * @return kategoria automobilu.
     */
    @Override
    public int getKategoriaInt() {
        return 1;
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
     * Znaková reprezentácia objektu typu Automobil.
     * @return String
     */
    @Override
    public String toString() {
        return "Automobil{" + "karoseria=" + karoseria + ", pocetDveri=" + pocetDveri + ", pocetSedadiel=" + pocetSedadiel + ", " + super.toString() + '}';
    }

    
	

}