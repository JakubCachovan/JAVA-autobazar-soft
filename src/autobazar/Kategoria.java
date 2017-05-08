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
public interface Kategoria extends Serializable{
    int getKategoriaInt();
    int getCena();
    String getZnacka();
    String getModel();
    int getNajazdeneKM();
    String getPalivo();
    int getRokVyroby();
    StavVozidla getStav();
    int getVykon();
    String getPopis();
    int celkovaHodnotaVozidiel();
    boolean obsahujeKlucoveSlovo(String keyWord);
            
}
