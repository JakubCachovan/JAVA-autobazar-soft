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
public enum StavVozidla implements Serializable{
    Nove,
    Zachovale,
    Ojazdene,
    Burane,
    NahradneDiely;

    @Override
    public String toString() {
        return "StavVozidla{" + this.name() + '}';
    }
    
    
}
