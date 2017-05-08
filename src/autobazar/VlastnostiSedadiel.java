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
public class VlastnostiSedadiel implements Serializable{
    private boolean standart;
    private boolean polohovatelne;
    private boolean posuvne;
    private boolean nastavitelne;      

    public VlastnostiSedadiel() {
    }

    public boolean isStandart() {
        return standart;
    }

    public void setStandart(boolean standart) {
        this.standart = standart;
    }

    public boolean isPolohovatelne() {
        return polohovatelne;
    }

    public void setPolohovatelne(boolean polohovatelne) {
        this.polohovatelne = polohovatelne;
    }

    public boolean isPosuvne() {
        return posuvne;
    }

    public void setPosuvne(boolean posuvne) {
        this.posuvne = posuvne;
    }

    public boolean isNastavitelne() {
        return nastavitelne;
    }

    public void setNastavitelne(boolean nastavitelne) {
        this.nastavitelne = nastavitelne;
    }

    public String vratRetazec(){
        String ret = "";
        if(standart) ret += "Štandart, ";
        if(polohovatelne)ret += "Polohovateľné, ";
        if(posuvne) ret += "Posuvné do uličky, ";
        if(nastavitelne) ret += "Nastaviteľné, ";
               
        return ret;        
    }
    
    @Override
    public String toString() {
        return "VlastnostiSedadiel{" + "standart=" + standart + ", polohovatelne=" + polohovatelne + ", posuvne=" + posuvne + ", nastavitelne=" + nastavitelne + '}';
    }

    

}


