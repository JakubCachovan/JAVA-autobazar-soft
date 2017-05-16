package autobazar;

import java.io.Serializable;

/**
 * Abstraktná trieda reprezentujúca vozidlo.
 * Predstavuje predka všetkých kategórií vozidiel.
 * @author Cachovan Jakub
 * @version 1.0
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
     * @param cena - cena vozidla
     * @param model - model vozidla
     * @param najazdeneKM  - najazdene KM vozidla
     * @param palivo - palivo vozidla
     * @param rokVyroby - rok vyroby vozidla
     * @param stav - stav vozidla
     * @param vykon - vykon vozidla
     * @param znacka - znacka vozidla
     * @param popis - popis vozidla
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
     * @return popis vozidla.
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Setter pre popis vozidla.
     * @param popis popis vozidla.
     */
    public void setPopis(String popis) {
        this.popis = popis;
    }

    /**
     * Getter pre cenu vozidla.
     * @return cena vozidla.
     */
    public int getCena() {
        return cena;
    }

    /**
     * Setter pre cenu vozidla.
     * @param cena cena vozidla.
     */
    public void setCena(int cena) {
        this.cena = cena;
    }
    
    /**
     * Getter pre model vozidla.
     * @return model vozidla.
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter pre model vozidla.
     * @param model model vozidla.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter pre najazdene km vozidla.
     * @return najazdene km vozidla.
     */
    public int getNajazdeneKM() {
        return najazdeneKM;
    }

    /**
     * Setter pre najazdene km vozidla.
     * @param najazdeneKM najazdene km vozidla.
     */
    public void setNajazdeneKM(int najazdeneKM) {
        this.najazdeneKM = najazdeneKM;
    }

    /**
     * Getter pre palivo vozidla.
     * @return palivo vozidla.
     */
    public String getPalivo() {
        return palivo;
    }

    /**
     * Setter pre palivo vozidla.
     * @param palivo palivo vozidla.
     */
    public void setPalivo(String palivo) {
        this.palivo = palivo;
    }

    /**
     * Getter pre rok vyroby vozidla.
     * @return rok vyroby vozidla.
     */
    public int getRokVyroby() {
        return rokVyroby;
    }

    /**
     * Setter pre rok vyroby vozidla.
     * @param rokVyroby rok vyroby vozidla.
     */
    public void setRokVyroby(int rokVyroby) {
        this.rokVyroby = rokVyroby;
    }

    /**
     * Getter pre objekt typu StavVozidla.
     * @return objekt typu StavVozidla.
     */
    public StavVozidla getStav() {
        return stav;
    }

    /**
     * Setter pre stav vozidla.
     * @param stav objekt typu StavVozidla.
     */
    public void setStav(StavVozidla stav) {
        this.stav = stav;
    }

    /**
     * Getter pre vykon vozidla.
     * @return vykon vozidla.
     */
    public int getVykon() {
        return vykon;
    }

    /**
     * Setter pre vykon vozidla.
     * @param vykon vykon vozidla.
     */
    public void setVykon(int vykon) {
        this.vykon = vykon;
    }

    /**
     * Getter pre značku vozidla.
     * @return značku vozidla.
     */
    public String getZnacka() {
        return znacka;
    }

    /**
     * Setter pre značku vozidla.
     * @param znacka značku vozidla.
     */
    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    /**
     * Znaková reprezentácia objektu typu Vozidlo.
     * @return String
     */
    @Override
    public String toString() {
        return "Vozidlo{" + "cena=" + cena + ", model=" + model + ", najazdeneKM=" + najazdeneKM + ", palivo=" + palivo + ", rokVyroby=" + rokVyroby + ", stav=" + stav + ", vykon=" + vykon + ", znacka=" + znacka + ", popis=" + popis + '}';
    }

}