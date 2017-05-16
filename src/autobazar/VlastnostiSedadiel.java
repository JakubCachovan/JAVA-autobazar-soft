package autobazar;

import java.io.Serializable;

/**
 * Reprezentuje vlastnosti sedadiel autobusu.
 * Každý atribút predstavuje položku možnej výbavy autobusu.
 * Na základe true/false hodnot je možné priradit výbavu pre autobus.
 * @author Jakub Cachovan
 */
public class VlastnostiSedadiel implements Serializable{
    private boolean standart;
    private boolean polohovatelne;
    private boolean posuvne;
    private boolean nastavitelne;      

    /**
     * Bezparametrický konštruktor na vytvorenie objektu typu VlasnotiSedadiel.
     */
    public VlastnostiSedadiel() {
        standart = false;
        polohovatelne = false;
        posuvne = false;
        nastavitelne = false;
    }

    /**
     * true -> ak je su sedadla štandartne
     * inak false
     * @return 
     */
    public boolean isStandart() {
        return standart;
    }

    /**
     * Setter pre nastavenie atributu standart.
     * Standartne sedadla -> true inak false
     * @param standart 
     */
    public void setStandart(boolean standart) {
        this.standart = standart;
    }

    /**
     * true -> ak su sedadla polohovatelne
     * inak false
     * @return 
     */
    public boolean isPolohovatelne() {
        return polohovatelne;
    }

    /**
     * Setter pre polohovatelne sedadla v autobuse.
     * @param polohovatelne 
     */
    public void setPolohovatelne(boolean polohovatelne) {
        this.polohovatelne = polohovatelne;
    }

    /**
     * Indikator či su sedadla posuvne.
     * @return 
     */
    public boolean isPosuvne() {
        return posuvne;
    }

    /**
     * Setter pre indikator posuvne.
     * @param posuvne 
     */
    public void setPosuvne(boolean posuvne) {
        this.posuvne = posuvne;
    }

    /**
     * Indikator či su sedadla nastavitelne.
     * @return 
     */
    public boolean isNastavitelne() {
        return nastavitelne;
    }

    /**
     * Setter pre indikator nastavitelne.
     * @param nastavitelne 
     */
    public void setNastavitelne(boolean nastavitelne) {
        this.nastavitelne = nastavitelne;
    }

    /**
     * Znaková reprezentácia atribútov z hodnotou true.
     * @return 
     */
    public String vratRetazec(){
        String ret = "";
        if(standart) ret += "Štandart, ";
        if(polohovatelne)ret += "Polohovateľné, ";
        if(posuvne) ret += "Posuvné do uličky, ";
        if(nastavitelne) ret += "Nastaviteľné, ";
               
        return ret;        
    }
    
    /**
     * Znaková reprezentácia objektu typu vlasnosti sedadiel.
     * @return 
     */
    @Override
    public String toString() {
        return "VlastnostiSedadiel{" + "standart=" + standart + ", polohovatelne=" + polohovatelne + ", posuvne=" + posuvne + ", nastavitelne=" + nastavitelne + '}';
    }

    

}


