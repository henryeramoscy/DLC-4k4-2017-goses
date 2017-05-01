/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses;

import java.io.Serializable;

/**
 *
 * @author pixelhar
 */
public class Termino implements Serializable{
    private String palabra;

    
    public Termino() {
        super();
    }
    
    public Termino(String palabra) {
        super();
        this.palabra = palabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
     

    @Override
    public String toString() {
        return "Vocabulario{" + "palabra=" + palabra+'}';
    }
    
    
    
    
    
}
