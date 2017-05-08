/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autobazar;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class BatozinovyPriestor implements Serializable{
    private boolean nosicLyzi;
    private boolean ziadny;
    private boolean klasicky;
    private boolean vysoky;

    public BatozinovyPriestor() {
        nosicLyzi = false;
        ziadny = false;
        klasicky = false;
        vysoky = false;
    }

    public boolean isNosicLyzi() {
        return nosicLyzi;
    }

    public void setNosicLyzi(boolean nosicLyzi) {
        this.nosicLyzi = nosicLyzi;
    }

    public boolean isZiadny() {
        return ziadny;
    }

    public void setZiadny(boolean ziadny) {
        this.ziadny = ziadny;
    }

    public boolean isKlasicky() {
        return klasicky;
    }

    public void setKlasicky(boolean klasicky) {
        this.klasicky = klasicky;
    }

    public boolean isVysoky() {
        return vysoky;
    }

    public void setVysoky(boolean vysoky) {
        this.vysoky = vysoky;
    }

    public String vratRetazec(){
        String ret = "";
        if(ziadny) ret += "Žiadny, ";
        if(nosicLyzi)ret += "Nosič lyží, ";
        if(klasicky) ret += "Klasický, ";
        if(vysoky) ret += "Vysoký, ";
               
        return ret;        
    }
    
    @Override
    public String toString() {
        return "BatozinovyPriestor{" + "nosicLyzi=" + nosicLyzi + ", ziadny=" + ziadny + ", klasicky=" + klasicky + ", vysoky=" + vysoky + '}';
    }
    
    
    
    
}
