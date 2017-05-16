package autobazar;

import java.io.Serializable;

/**
 * Interface Kategoria predstavuje kategoriu vozidla v inzeráte.
 * Kategória môže byť Automobi, Motocykel, Autobus, Nákladné auto.
 * @author Jakub Cachovan
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
