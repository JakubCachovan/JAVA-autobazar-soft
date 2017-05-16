package autobazar;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import javax.swing.JOptionPane;

/**
 * Trieda reprezentujúca inzerát pre autobazar.
 * @author Cachovan Jakub
 * @version 1.0
 */
public class Inzerat implements Serializable{
        private Kategoria kategoria;
	private Date DatumVytvorenia;
	private int ID;
        private Predajca predajca;
        private StavInzeratu stav;

    public Inzerat(){}
    
    /**
     * Konštruktor pre vytvorenie objektu typu Inzerat
     * @param paKategoria - objekt typu Kategoria
     * @param paPredajca - objekt typu Predajca
     */
    public Inzerat(Kategoria paKategoria, Predajca paPredajca){
        DatumVytvorenia = new Date(java.util.Date.from(Instant.now()).getTime());
        predajca = paPredajca;
        kategoria = paKategoria;
        stav = StavInzeratu.Aktivne;
    }
    
    /**
     * Metóda pre priradenie predajcu pre inzerát.
     * @param Email - emailová adresa predajcu
     * @param Lokalita - lokalita v ktorej býva predajca
     * @param Meno - meno predajcu 
     * @param Priezvisko - priezvisko predajcu
     * @param Telefon - telefon predajcu
     * @return true/false
     */
    public boolean priraditPredajcu(String Email, String Lokalita, String Meno, String Priezvisko, String Telefon){
        try {
            predajca = new Predajca(Email, Lokalita, Meno, Priezvisko, Telefon);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    /**
     * Vrátenie hodnoty ktorá reprezentuje typ objektu.
     * 1-Automobil, 2-Autobus, 3-Motocykel, 4-Nakladne auto
     * @return identifikátor kategorie
     */
    public int getInstanceKategoria(){
        if(kategoria instanceof Automobil){
            return 1; // Auto
        }else if(kategoria instanceof Autobus){
            return 2;
        }else if(kategoria instanceof Motocykel){
            return 3;
        }else if(kategoria instanceof NakladneAuto){
            return 4;
        }
        JOptionPane.showMessageDialog(null, "Chyba!");
        return -1;
    }
    
    /**
     * Vrátenie názvu kategórie podľa toho, ktorá inštancia ju reprezentuje.
     * @return String
     */
    public String getKategoriaInstanceName(){
        if(kategoria instanceof Automobil){
            return "Automobil";
        }else if(kategoria instanceof Autobus){
            return "Autobus";
        }else if(kategoria instanceof Motocykel){
            return "Motocykel";
        }else if(kategoria instanceof NakladneAuto){
            return "Nákladné auto";
        }
        return "";
    }

    /**
     * Setter pre atribút predajca.
     * @param predajca - objekt typu Predajca
     */
    public void setPredajca(Predajca predajca) {
        this.predajca = predajca;
    }

    /**
     * Setter pre atribút stav.
     * @param stav - stav inzeratu, objekt typu StavInzeratu
     */
    public void setStav(StavInzeratu stav) {
        this.stav = stav;
    }

    /**
     * Getter pre atribút stav inzeratu.
     * @return objekt typu StavInzeratu
     */
    public StavInzeratu getStav() {
        return stav;
    }
       
    /**
     * Getter pre atribút kategória.
     * @return objekt typu Kategoria
     */
    public Kategoria getKategoria() {    
        return kategoria;
    }

    /**
     * Getter pre atribút datumVytvorenia.
     * @return dátum vytvorenia inzeratu
     */
    public Date getDatumVytvorenia() {
        return DatumVytvorenia;
    }

    /**
     * Getter pre atribút id inzeratu.
     * @return id inzeratu
     */
    public int getID() {
        return ID;
    }

    /**
     * Getter pre atribút predajca.
     * @return objekt typu Predajca
     */
    public Predajca getPredajca() {
        return predajca;
    }

    /**
     * Setter pre kategóriu vozidla.
     * @param kategoria objekt typu Kategoria
     */
    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    /**
     * Setter pre dátum vytvorenia inzerátu.
     * @param DatumVytvorenia - datum vytvorenia inzeratu
     */
    public void setDatumVytvorenia(Date DatumVytvorenia) {
        this.DatumVytvorenia = DatumVytvorenia;
    }

    /**
     * Setter pre id inzerátu.
     * @param ID - id inzeratu
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Znaková reprezentácia objektu typu Inzerat.
     * @return String
     */
    @Override
    public String toString() {
        return "Inzerat{" + "kategoria=" + kategoria + ", DatumVytvorenia=" + DatumVytvorenia + ", ID=" + ID + ", predajca=" + predajca + ", stav=" + stav + '}';
    }

}