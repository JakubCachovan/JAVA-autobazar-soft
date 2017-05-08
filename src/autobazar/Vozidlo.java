package autobazar;

import java.io.Serializable;


/**
 * neda sa vytvorit instancia triedy Vozidlo ale iba jej potomkov. napr: Vozidlo v
 * = new Motocykel()
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:52
 */
public abstract class Vozidlo implements Kategoria, Serializable{

	private int cena;
	private String model;
	private int najazdeneKM;
	private String palivo;
	private int rokVyroby;
	private StavVozidla stav;
	private int vykon;
	private String znacka;
        private String popis;
        //private int id_vozidla;

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

    /*public int getId_vozidla() {
        return id_vozidla;
    }

    public void setId_vozidla(int id_vozidla) {
        this.id_vozidla = id_vozidla;
    }*/
   
    /**
     *
     * @return
     */
    @Override
    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    
        @Override
    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

        @Override
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

        @Override
    public int getNajazdeneKM() {
        return najazdeneKM;
    }

    public void setNajazdeneKM(int najazdeneKM) {
        this.najazdeneKM = najazdeneKM;
    }

        @Override
    public String getPalivo() {
        return palivo;
    }

    public void setPalivo(String palivo) {
        this.palivo = palivo;
    }

        @Override
    public int getRokVyroby() {
        return rokVyroby;
    }

    public void setRokVyroby(int rokVyroby) {
        this.rokVyroby = rokVyroby;
    }

    /**
     *
     * @return
     */
    @Override
    public StavVozidla getStav() {
        return stav;
    }

    public void setStav(StavVozidla stav) {
        this.stav = stav;
    }

        @Override
    public int getVykon() {
        return vykon;
    }

    public void setVykon(int vykon) {
        this.vykon = vykon;
    }

        @Override
    public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    @Override
    public String toString() {
        return "Vozidlo{" + "cena=" + cena + ", model=" + model + ", najazdeneKM=" + najazdeneKM + ", palivo=" + palivo + ", rokVyroby=" + rokVyroby + ", stav=" + stav + ", vykon=" + vykon + ", znacka=" + znacka + ", popis=" + popis + '}';
    }
    
    
    
}