package autobazar;

import java.io.Serializable;

/**
 * Enum pre stav vozidla.
 * @author Jakub Cachovan
 */
public enum StavVozidla implements Serializable{
    Nove,
    Zachovale,
    Ojazdene,
    Burane,
    NahradneDiely;

    /**
     * Znaková reprezentácia objektu.
     * @return String
     */
    @Override
    public String toString() {
        return "StavVozidla{" + this.name() + '}';
    }
    
    
}
