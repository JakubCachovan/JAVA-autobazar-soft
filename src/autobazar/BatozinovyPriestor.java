package autobazar;

import java.io.Serializable;

/**
 * Trieda reprezentujuca batozinový priestor pre autobus.
 * Obsahuje atribúty reprezentujúce výbavu ohľadom batožinového priestoru.
 * @author Jakub Cachovan
 */
public class BatozinovyPriestor implements Serializable{
    private boolean nosicLyzi;
    private boolean ziadny;
    private boolean klasicky;
    private boolean vysoky;

    /**
     * Trieda pre vytvorenie objektu typu BatozinovyPriestor
     */
    public BatozinovyPriestor() {
        nosicLyzi = false;
        ziadny = false;
        klasicky = false;
        vysoky = false;
    }

    /**
     * Zistí, či je vo vybave nosič lyží -> true, inak false.
     * @return true/false
     */
    public boolean isNosicLyzi() {
        return nosicLyzi;
    }

    /**
     * 
     * @param nosicLyzi 
     */
    public void setNosicLyzi(boolean nosicLyzi) {
        this.nosicLyzi = nosicLyzi;
    }

    /**
     * Ak neexistuje bytožinový priestor -> true, inak false.
     * @return true/false
     */
    public boolean isZiadny() {
        return ziadny;
    }

    /**
     * Nastaví atribut pre žiadny batožinový priestor.
     * @param ziadny 
     */
    public void setZiadny(boolean ziadny) {
        this.ziadny = ziadny;
    }

    /**
     * Klasický batožinový priestor -> true, inak false.
     * @return true/false
     */
    public boolean isKlasicky() {
        return klasicky;
    }

    /**
     * Nastaví hodnotu atributu klasicky.
     * @param klasicky - klasicky batožinový priestor
     */
    public void setKlasicky(boolean klasicky) {
        this.klasicky = klasicky;
    }

    /**
     * Vysoký batožinový priestor -> true, inak false.
     * @return true/false
     */
    public boolean isVysoky() {
        return vysoky;
    }

    /**
     * Nastaví hodnotu atributu vysoky.
     * @param vysoky 
     */
    public void setVysoky(boolean vysoky) {
        this.vysoky = vysoky;
    }

    /**
     * Generuje retazec na základe true hodnot atribútov.
     * @return String
     */
    public String vratRetazec(){
        String ret = "";
        if(ziadny) ret += "Žiadny, ";
        if(nosicLyzi)ret += "Nosič lyží, ";
        if(klasicky) ret += "Klasický, ";
        if(vysoky) ret += "Vysoký, ";
               
        return ret;        
    }
    
    /**
     * Znaková reprezentácia objektu.
     * @return 
     */
    @Override
    public String toString() {
        return "BatozinovyPriestor{" + "nosicLyzi=" + nosicLyzi + ", ziadny=" + ziadny + ", klasicky=" + klasicky + ", vysoky=" + vysoky + '}';
    }
    
    
    
    
}
