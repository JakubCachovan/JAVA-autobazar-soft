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

    public NakladneAuto(String druh, int hmotnostVozidla, String normaEmisii, int nosnost, int cena, String model, int najazdeneKM, String palivo, int rokVyroby, StavVozidla stav, int vykon, String znacka, String popis) {
        super(cena, model, najazdeneKM, palivo, rokVyroby, stav, vykon, znacka, popis);
        this.druh = druh;
        this.hmotnostVozidla = hmotnostVozidla;
        this.normaEmisii = normaEmisii;
        this.nosnost = nosnost;
    }
    
    @Override
    public boolean obsahujeKlucoveSlovo(String keyWord) {      
        Pattern p = Pattern.compile("("+keyWord+")");
        Matcher m = p.matcher(this.toString());
        return m.find();       
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
        
    
    public int getHmotnostVozidla() {
        return hmotnostVozidla;
    }

    public void setHmotnostVozidla(int hmotnostVozidla) {
        this.hmotnostVozidla = hmotnostVozidla;
    }

    public String getNormaEmisii() {
        return normaEmisii;
    }

    public void setNormaEmisii(String normaEmisii) {
        this.normaEmisii = normaEmisii;
    }

    public int getNosnost() {
        return nosnost;
    }

    public void setNosnost(int nosnost) {
        this.nosnost = nosnost;
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
    public int getKategoriaInt() {
        return 3;
    }

    
    @Override
    public int getCena() {
        return super.getCena(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "NakladneAuto{" + "druh=" + druh + ", hmotnostVozidla=" + hmotnostVozidla + ", normaEmisii=" + normaEmisii + ", nosnost=" + nosnost + ", " + super.toString() +'}';
    }

    

}