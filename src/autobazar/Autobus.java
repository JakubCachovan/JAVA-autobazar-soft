package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:51
 */
public class Autobus extends Vozidlo implements Kategoria, Serializable{

        private String druh; 
        private String normaEmisii;
	private BatozinovyPriestor batozinovyPriestor;
	private String nadstavba;
	private int pocetMiestNaSedenie;
	private VlastnostiSedadiel vlastnostiSedadiel;

    public Autobus(String druh, String normaEmisii, BatozinovyPriestor batozinovyPriestor, String nadstavba, int pocetMiestNaSedenie, VlastnostiSedadiel vlastnostiSedadiel, int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        super(cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
        this.druh = druh;
        this.normaEmisii = normaEmisii;
        this.batozinovyPriestor = batozinovyPriestor;
        this.nadstavba = nadstavba;
        this.pocetMiestNaSedenie = pocetMiestNaSedenie;
        this.vlastnostiSedadiel = vlastnostiSedadiel;
    }
    
    

    @Override
    public int celkovaHodnotaVozidiel() {
        return super.getCena();
    }   

    public String getDruh() {
        return druh;
    }

    public void setDruh(String druh) {
        this.druh = druh;
    }

    public String getNormaEmisii() {
        return normaEmisii;
    }

    public void setNormaEmisii(String normaEmisii) {
        this.normaEmisii = normaEmisii;
    }

    public BatozinovyPriestor getBatozinovyPriestor() {
        return batozinovyPriestor;
    }

    public void setBatozinovyPriestor(BatozinovyPriestor batozinovyPriestor) {
        this.batozinovyPriestor = batozinovyPriestor;
    }

    public String getNadstavba() {
        return nadstavba;
    }

    public void setNadstavba(String nadstavba) {
        this.nadstavba = nadstavba;
    }

    public int getPocetMiestNaSedenie() {
        return pocetMiestNaSedenie;
    }

    public void setPocetMiestNaSedenie(int pocetMiestNaSedenie) {
        this.pocetMiestNaSedenie = pocetMiestNaSedenie;
    }

    public VlastnostiSedadiel getVlastnostiSedadiel() {
        return vlastnostiSedadiel;
    }

    public void setVlastnostiSedadiel(VlastnostiSedadiel vlastnostiSedadiel) {
        this.vlastnostiSedadiel = vlastnostiSedadiel;
    }

    @Override
    public void setZnacka(String znacka) {
        super.setZnacka(znacka); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getZnacka() {
        return super.getZnacka(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVykon(int vykon) {
        super.setVykon(vykon); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getVykon() {
        return super.getVykon(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPopis(String popis) {
        super.setPopis(popis); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPopis() {
        return super.getPopis(); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void setStav(StavVozidla stav) {
        super.setStav(stav); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StavVozidla getStav() {
        return super.getStav(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRokVyroby(int rokVyroby) {
        super.setRokVyroby(rokVyroby); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRokVyroby() {
        return super.getRokVyroby(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPalivo(String palivo) {
        super.setPalivo(palivo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPalivo() {
        return super.getPalivo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNajazdeneKM(int najazdeneKM) {
        super.setNajazdeneKM(najazdeneKM); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNajazdeneKM() {
        return super.getNajazdeneKM(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModel(String model) {
        super.setModel(model); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModel() {
        return super.getModel(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCena(int cena) {
        super.setCena(cena); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCena() {
        return super.getCena(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getKategoriaInt() {
        return 4;
    }

    @Override
    public String toString() {
        return "Autobus{" + "druh=" + druh + ", normaEmisii=" + normaEmisii + ", batozinovyPriestor=" + batozinovyPriestor + ", nadstavba=" + nadstavba + ", pocetMiestNaSedenie=" + pocetMiestNaSedenie + ", vlastnostiSedadiel=" + vlastnostiSedadiel + ", " + super.toString() +'}';
    }

    @Override
    public boolean obsahujeKlucoveSlovo(String keyWord) {      
        Pattern p = Pattern.compile("("+keyWord+")");
        Matcher m = p.matcher(this.toString());
        return m.find();       
    }

    
	
}