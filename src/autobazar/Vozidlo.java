package autobazar;

import java.io.Serializable;

/**
 * Abstraktná trieda reprezentujúca vozidlo.
 * Predstavuje predka všetkých kategórií vozidiel.
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:52
 */
public abstract class Vozidlo implements Serializable{

	protected int cena;
	protected String model;
	protected int najazdeneKM;
	protected String palivo;
	protected int rokVyroby;
	protected StavVozidla stav;
	protected int vykon;
	protected String znacka;
        protected String popis;

    /**
     * Konštruktor pre vytvorenie objektu typu Vozidlo.
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
    public Vozidlo(int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        this.cena = cena;
        this.model = model;
        this.najazdeneKM = najazdeneKM;
        this.palivo = palivo;
        this.rokVyroby = rokVyroby;
        this.stav = stav;
        this.vykon = vykon;
        this.znacka = znacka;
        this.popis = popis;
    }
   
    /**
     * Getter pre popis vozidla.
     * @return
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Setter pre popis vozidla.
     * @param popis 
     */
    public void setPopis(String popis) {
        this.popis = popis;
    }

    /**
     * Getter pre cenu vozidla.
     * @return 
     */
    public int getCena() {
        return cena;
    }

    /**
     * Setter pre cenu vozidla.
     * @param cena 
     */
    public void setCena(int cena) {
        this.cena = cena;
    }
    
    /**
     * Getter pre model vozidla.
     * @return 
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter pre model vozidla.
     * @param model 
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter pre najazdene km vozidla.
     * @return 
     */
    public int getNajazdeneKM() {
        return najazdeneKM;
    }

    /**
     * Setter pre najazdene km vozidla.
     * @param najazdeneKM 
     */
    public void setNajazdeneKM(int najazdeneKM) {
        this.najazdeneKM = najazdeneKM;
    }

    /**
     * Getter pre palivo vozidla.
     * @return 
     */
    public String getPalivo() {
        return palivo;
    }

    /**
     * Setter pre palivo vozidla.
     * @param palivo 
     */
    public void setPalivo(String palivo) {
        this.palivo = palivo;
    }

    /**
     * Getter pre rok vyroby vozidla.
     * @return 
     */
    public int getRokVyroby() {
        return rokVyroby;
    }

    /**
     * Setter pre rok vyroby vozidla.
     * @param rokVyroby 
     */
    public void setRokVyroby(int rokVyroby) {
        this.rokVyroby = rokVyroby;
    }

    /**
     * Getter pre objekt typu StavVozidla.
     * @return
     */
    public StavVozidla getStav() {
        return stav;
    }

    /**
     * Setter pre popis vozidla.
     * @param stav 
     */
    public void setStav(StavVozidla stav) {
        this.stav = stav;
    }

    /**
     * Getter pre vykon vozidla.
     * @return 
     */
    public int getVykon() {
        return vykon;
    }

    /**
     * Setter pre vykon vozidla.
     * @param vykon 
     */
    public void setVykon(int vykon) {
        this.vykon = vykon;
    }

    /**
     * Getter pre značku vozidla.
     * @return 
     */
    public String getZnacka() {
        return znacka;
    }

    /**
     * Setter pre značku vozidla.
     * @param znacka 
     */
    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    /**
     * Znaková reprezentácia objektu typu Vozidlo.
     * @return 
     */
    @Override
    public String toString() {
        return "Vozidlo{" + "cena=" + cena + ", model=" + model + ", najazdeneKM=" + najazdeneKM + ", palivo=" + palivo + ", rokVyroby=" + rokVyroby + ", stav=" + stav + ", vykon=" + vykon + ", znacka=" + znacka + ", popis=" + popis + '}';
    }

}