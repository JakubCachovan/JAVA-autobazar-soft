package autobazar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


/**
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:51
 */
public class Autobazar implements Serializable{

    /**
     * private int mnozsvtoInzeratov;
     */
    //private String nazov;
    private ArrayList<Inzerat> zoznamInzeratov;
    private ArrayList<Predajca> zoznamPredajcov;
    private ArrayList<Kategoria> zoznamVozidiel;

    public Autobazar(){
        zoznamInzeratov = new ArrayList<>();   
        zoznamPredajcov = new ArrayList<>();
        zoznamVozidiel = new ArrayList<>();
    }

    public Inzerat VytvoritInzerat(Kategoria paKategoria, Predajca paPredajca){
        try {
            zoznamVozidiel.add(paKategoria);
            Inzerat inz = new Inzerat(paKategoria, paPredajca);
            inz.setID(vytvoritID());
            zoznamInzeratov.add(inz);
            if(!zoznamPredajcov.contains(paPredajca)){
                zoznamPredajcov.add(paPredajca);
            }            
            return inz; // este nema ID
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public int hodnotaVsetkychVozidiel(){
        int hodnota = 0;
        for (Kategoria kategoria : zoznamVozidiel) {
            hodnota += kategoria.celkovaHodnotaVozidiel();
        }
        return hodnota;
    }
    
    public ArrayList<Inzerat> keyWordSearch(String klucoveSlovo){
        //hladanie v predajcoch
        ArrayList<Inzerat> najdeneInzeraty = new ArrayList<>();
        for (Inzerat inzerat : zoznamInzeratov) {
            if(inzerat.getKategoria().obsahujeKlucoveSlovo(klucoveSlovo)){
                najdeneInzeraty.add(inzerat);
            }          
        }
        return najdeneInzeraty;
    }
    
    public int vytvoritID(){
        if(zoznamInzeratov.isEmpty()){
            return 0;
        }else{                    
            int poslednyIndex = (zoznamInzeratov.size()-1);
            int posledneID = zoznamInzeratov.get(poslednyIndex).getID();
            return ++posledneID;
        }     
    }
    
    public Predajca najdiPredajcu(String email){
        for (Predajca p : zoznamPredajcov) {
            if(p.getEmail().equalsIgnoreCase(email)){
                return p;
            }
        }
        return null;
    }
    
    public Kategoria najdiKategoriu(int idInzeratu){
        for (Inzerat inzerat : zoznamInzeratov) {
            if(inzerat.getID() == idInzeratu){
                return inzerat.getKategoria();
            }
        }
        JOptionPane.showMessageDialog(null, "Vozidlo sa nenašlo...");
        return null;
    }
    
    public Inzerat najdiInzerat(int idInzeratu){
        for (Inzerat inzerat : zoznamInzeratov) {
            if(inzerat.getID() == idInzeratu){
                return inzerat;
            }
        }
        JOptionPane.showMessageDialog(null, "Inzerat sa nenasiel...");
        return null;
    }

    
    public boolean vymazatPredajcu(String email){

        for (Predajca predajca : zoznamPredajcov) {
            if(predajca.getEmail().equalsIgnoreCase(email)){
                zoznamPredajcov.remove(predajca);
                return true;
            } 
        }
        return false;
    }
    public void vymazatInzerat(Inzerat i){
        zoznamInzeratov.remove(i);
    }
    public boolean vymazatInzerat(int idInzeratu){
        for (Inzerat inzerat : zoznamInzeratov) {
            if(inzerat.getID() == idInzeratu){
                zoznamInzeratov.remove(inzerat);
                return true;
            }
        }    
        return false;
    }
    
    public boolean setAktivne(int idInzeratu){
        try {
            for (Inzerat inzerat : zoznamInzeratov) {
                if(inzerat.getID() == idInzeratu){
                    inzerat.setStav(StavInzeratu.Aktivne);
                    return true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);     
        }
        return false;
    }
    
    public boolean getAktivne(int idInzeratu){
        try {
            for (Inzerat inzerat : zoznamInzeratov) {
                if(inzerat.getID() == idInzeratu){
                    if(inzerat.getStav().name() == StavInzeratu.Aktivne.name()){
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);                     
        }
        return false;
    }
    
    public boolean setPredane(int idInzeratu){
        try {
            for (Inzerat inzerat : zoznamInzeratov) {
                if(inzerat.getID() == idInzeratu){
                    inzerat.setStav(StavInzeratu.Predane);
                    return true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);          
        }       
        return false;
    }
    
    public boolean getPredane(int idInzeratu){
        try {
            for (Inzerat inzerat : zoznamInzeratov) {
                if(inzerat.getID() == idInzeratu){
                    if(inzerat.getStav().name() == StavInzeratu.Predane.name()){
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);                     
        }
        return false;
    }

    public ArrayList<Inzerat> getZoznamInzeratov() {
        return zoznamInzeratov;
    }

    public ArrayList<Predajca> getZoznamPredajcov() {
        return zoznamPredajcov;
    }

    public void setZoznamPredajcov(ArrayList<Predajca> zoznamPredajcov) {
        this.zoznamPredajcov = zoznamPredajcov;
    }

    public void setZoznamInzeratov(ArrayList<Inzerat> zoznamInzeratov) {
        this.zoznamInzeratov = zoznamInzeratov;
    }
    

    public ArrayList<Automobil> getZoznamAutomobilov(){
        try {
            ArrayList<Automobil> zoznamAutomobilov = new ArrayList<>();
            for (Inzerat inzerat : zoznamInzeratov) {
                if(inzerat.getKategoria() instanceof Automobil){
                    zoznamAutomobilov.add((Automobil)inzerat.getKategoria());
                }
            }
            return zoznamAutomobilov;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }      
        return null;
    }
    public ArrayList<Autobus> getZoznamAutobusov(){
        try {
            ArrayList<Autobus> zoznamAutobusov = new ArrayList<>();
            for (Inzerat inzerat : zoznamInzeratov) {
                if(inzerat.getKategoria() instanceof Autobus){
                    zoznamAutobusov.add((Autobus)inzerat.getKategoria());
                }
            }
            return zoznamAutobusov;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }      
        return null;
    }

    public ArrayList<Kategoria> getZoznamVozidiel() {
        return zoznamVozidiel;
    }

    public void setZoznamVozidiel(ArrayList<Kategoria> zoznamVozidiel) {
        this.zoznamVozidiel = zoznamVozidiel;
    }
    
    
    public static boolean saveToFile(File f, Autobazar a) throws IOException{
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));
            os.writeObject(a);
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }       
    }
    
    public static Autobazar loadFromFile(File f){
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(f.getAbsoluteFile()));
            Autobazar autobazar = (Autobazar)is.readObject();
            is.close();
            return autobazar;
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    @Override
    public String toString() {
        return "Autobazar{" + "zoznamInzeratov=" + zoznamInzeratov + '}';
    }
        
        
        
}