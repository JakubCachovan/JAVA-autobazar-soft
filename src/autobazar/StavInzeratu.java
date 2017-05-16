package autobazar;

import java.io.Serializable;

/**
 * Enum pre stav inzerátu
 * @author Jakub Cachovan
 */
public enum StavInzeratu implements Serializable{
    Aktivne,
    Predane;

    /**
     * Znaková reprezentácia objektu.
     * @return String
     */
    @Override
    public String toString() {
        return "StavInzeratu{"+ this.name() + '}';
    }
    
    
}
