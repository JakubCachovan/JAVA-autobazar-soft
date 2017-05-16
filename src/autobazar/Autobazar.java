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
import javax.swing.JOptionPane;


/**
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:51
 */
public class Autobazar implements Serializable{

    private ArrayList<Inzerat> zoznamInzeratov;
    private ArrayList<Predajca> zoznamPredajcov;
    private ArrayList<Kategoria> zoznamVozidiel;

    /**
     * Konštruktor pre vytvorenie objektu typu Autobazar
     * Inicializuje zoznam inzeratov, predajcov a vozidiel
     */
    public Autobazar(){
        zoznamInzeratov = new ArrayList<>();   
        zoznamPredajcov = new ArrayList<>();
        zoznamVozidiel = new ArrayList<>();
    }
    
    /**
     * Vytvorenie inzerátu za základe kategórie vozidla a predajcu.
     * @param paKategoria - objekt typu Kategoria
     * @param paPredajca - objekt typu Predajca
     * @return objekt typu Inzerat
     */
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
    
    /**
     * Metóda pre výpočet cenovej hodnoty všetkých vozidiel v autobazáre.
     * @return integer
     */
    public int hodnotaVsetkychVozidiel(){
        int hodnota = 0;
        for (Kategoria kategoria : zoznamVozidiel) {
            hodnota += kategoria.celkovaHodnotaVozidiel();
        }
        return hodnota;
    }
    
    /**
     * Metóda na vyhľadávanie inzerátov podľa kľúčového slova.
     * Inzeráty zhodné s kľúčových slovom sú návratovou hodnotou metódy.
     * @param klucoveSlovo - kľúčové slovo pre vyhľadávanie
     * @return zoznam inzerátov 
     */
    public ArrayList<Inzerat> keyWordSearch(String klucoveSlovo){
        //hladanie v predajcoch
        ArrayList<Inzerat> najdeneInzeraty = new ArrayList<>();
        for (Inzerat inzerat : zoznamInzeratov) {
            if(inzerat.getKategoria().obsahujeKlucoveSlovo(klucoveSlovo)){
                najdeneInzeraty.add(inzerat);
            }else if(inzerat.getPredajca().obsahujeKlucoveSlovo(klucoveSlovo)){
                najdeneInzeraty.add(inzerat);
            }
        }
        return najdeneInzeraty;
    }
    
    /**
     * Metóda na vytvorenie identifikáčného čísla pre inzerát. 
     * Toto id je aj návratovou hodnotou metódy.
     * @return integer
     */
    public int vytvoritID(){
        if(zoznamInzeratov.isEmpty()){
            return 0;
        }else{                    
            int poslednyIndex = (zoznamInzeratov.size()-1);
            int posledneID = zoznamInzeratov.get(poslednyIndex).getID();
            return ++posledneID;
        }     
    }
    
    /**
     * Vyhľadávanie predajcu v zozname predajcov na základe emailu, ktorý je parametrom metódy.
     * @param email - emailová adresa predajcu
     * @return objekt typu Predajca
     */
    public Predajca najdiPredajcu(String email){
        for (Predajca p : zoznamPredajcov) {
            if(p.getEmail().equalsIgnoreCase(email)){
                return p;
            }
        }
        return null;
    }
    
    /**
     * Vyhľadávanie kategórie vozidla na základe id inzerátu.
     * @param idInzeratu - id inzerátu
     * @return objekt typu Kategoria
     */
    public Kategoria najdiKategoriu(int idInzeratu){
        for (Inzerat inzerat : zoznamInzeratov) {
            if(inzerat.getID() == idInzeratu){
                return inzerat.getKategoria();
            }
        }
        JOptionPane.showMessageDialog(null, "Vozidlo sa nenašlo...");
        return null;
    }
    
    /**
     * Vyhľadávanie inzerátu na základe id inzerátu.
     * @param idInzeratu - id inzerátu
     * @return objekt typu Inzerat
     */
    public Inzerat najdiInzerat(int idInzeratu){
        for (Inzerat inzerat : zoznamInzeratov) {
            if(inzerat.getID() == idInzeratu){
                return inzerat;
            }
        }
        JOptionPane.showMessageDialog(null, "Inzerat sa nenasiel...");
        return null;
    }

    /**
     * Metóda pre vymazanie predajcu zo zoznamu predajcov na základe emailu.
     * @param email - emailová adresa predajcu
     * @return true/false
     */
    public boolean vymazatPredajcu(String email){

        for (Predajca predajca : zoznamPredajcov) {
            if(predajca.getEmail().equalsIgnoreCase(email)){
                zoznamPredajcov.remove(predajca);
                return true;
            } 
        }
        return false;
    }
    
    /**
     * Vymazanie inzerátu zo zoznamu inzerátov.
     * @param inzerat - objekt typu Inzerat
     */
    public void vymazatInzerat(Inzerat inzerat){
        if(zoznamInzeratov.contains(inzerat)){
            zoznamInzeratov.remove(inzerat);
        }
    }
    
    /**
     * Vymazanie inzerátu zo zoznamu inzerátov na základe ID.
     * @param idInzeratu - id inzerátu
     * @return true ak uspesne vymazany, inak false
     */
    public boolean vymazatInzerat(int idInzeratu){
        for (Inzerat inzerat : zoznamInzeratov) {
            if(inzerat.getID() == idInzeratu){
                zoznamInzeratov.remove(inzerat);
                return true;
            }
        }    
        return false;
    }
    
    /**
     * Setter pre atribút stav inzerátu.
     * Nastaví stav inzerátu na "Aktívny".
     * @param idInzeratu - id inzeratu
     * @return true/false
     */
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
    
    /**
     * Getter pre stav inzerátu.
     * @param idInzeratu - id inzerátu
     * @return true/false
     */
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
    
    /**
     * Setter pre stav inzeratu na hodnotu "Predane".
     * @param idInzeratu - id inzeratu
     * @return true/false
     */
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
    
    /**
     * Getter pre stav inzerátu. 
     * @param idInzeratu - id inzeratu
     * @return true/false
     */
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

    /**
     * Getter pre zoznam inzeratov.
     * @return zoznam inzerátov
     */
    public ArrayList<Inzerat> getZoznamInzeratov() {
        return zoznamInzeratov;
    }

    /**
     * Getter pre zoznam predajcov.
     * @return zoznam Predajcov
     */
    public ArrayList<Predajca> getZoznamPredajcov() {
        return zoznamPredajcov;
    }

    /**
     * Setter pre zoznam predajcov.
     * @param zoznamPredajcov - zoznam predajcov 
     */
    public void setZoznamPredajcov(ArrayList<Predajca> zoznamPredajcov) {
        this.zoznamPredajcov = zoznamPredajcov;
    }

    /**
     * Setter pre zoznam inzeratov.
     * @param zoznamInzeratov - zoznam inzerátov
     */
    public void setZoznamInzeratov(ArrayList<Inzerat> zoznamInzeratov) {
        this.zoznamInzeratov = zoznamInzeratov;
    }
    
    /**
     * Getter pre zoznam automobilov
     * @return zoznam automobilov
     */
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
    
    /**
     * Getter pre zoznam autobusov
     * @return 
     */
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

    /**
     * Getter pre zoznam vozidiel
     * @return 
     */
    public ArrayList<Kategoria> getZoznamVozidiel() {
        return zoznamVozidiel;
    }

    /**
     * Setter pre zoznam vozidiel
     * @param zoznamVozidiel 
     */
    public void setZoznamVozidiel(ArrayList<Kategoria> zoznamVozidiel) {
        this.zoznamVozidiel = zoznamVozidiel;
    }
    
    /**
     * Uloženie dát do súboru
     * @param f - súbor
     * @return true/false 
     */
    public boolean saveToFile(File f){
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));
            os.writeObject(this);
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
    
    /**
     * Statická metóda pre načítanie objektu typu Autobazar zo súboru
     * @param f - súbor
     * @return objekt typu Autobazar / null
     */
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

    /**
     * Znaková reprezentácia objektu.
     * @return 
     */
    @Override
    public String toString() {
        return "Autobazar{" + "zoznamInzeratov=" + zoznamInzeratov + '}';
    }
        
        
        
}