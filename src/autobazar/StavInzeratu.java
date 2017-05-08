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
public enum StavInzeratu implements Serializable{
    Aktivne,
    Predane;

    @Override
    public String toString() {
        return "StavInzeratu{"+ this.name() + '}';
    }
    
    
}
