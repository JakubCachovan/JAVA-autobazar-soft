package autobazar;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import javax.swing.JOptionPane;

/**
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:52
 */
public class Inzerat implements Serializable{
        private Kategoria kategoria;
	private Date DatumVytvorenia;
	private int ID;
        private Predajca predajca;
        private StavInzeratu stav;

    public Inzerat(){}
    
    public Inzerat(Kategoria paKategoria, Predajca paPredajca){
        DatumVytvorenia = new Date(java.util.Date.from(Instant.now()).getTime());
        predajca = paPredajca;
        kategoria = paKategoria;
        stav = StavInzeratu.Aktivne;
    }
    
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
     * 1-> automobil, 2->Autobus, 3->Motocykel, 4->Nakladne auto
     * @return 
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
    
    public static int getInstanceKategoria(Kategoria kategoria){
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

    public void setPredajca(Predajca predajca) {
        this.predajca = predajca;
    }
    

    public void setStav(StavInzeratu stav) {
        this.stav = stav;
    }

    public StavInzeratu getStav() {
        return stav;
    }
       
    public Kategoria getKategoria() {    
        return kategoria;
    }

    public Date getDatumVytvorenia() {
        return DatumVytvorenia;
    }

    public int getID() {
        return ID;
    }

    public Predajca getPredajca() {
        return predajca;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public void setDatumVytvorenia(Date DatumVytvorenia) {
        this.DatumVytvorenia = DatumVytvorenia;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Inzerat{" + "kategoria=" + kategoria + ", DatumVytvorenia=" + DatumVytvorenia + ", ID=" + ID + ", predajca=" + predajca + ", stav=" + stav + '}';
    }

}