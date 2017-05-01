/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses.db;

import java.io.Serializable;

/**
 *
 * @author pixelhar
 */
public class DBVocabulario implements Serializable{
    private String palabra;
    private int nr;
    private int maxtf;
    
    public DBVocabulario() {
        super();
    }
    
    public DBVocabulario(String palabra, int nr, int maxtf) {
        super();
        this.palabra = palabra;
        this.nr = nr;
        this.maxtf = maxtf;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getMaxtf() {
        return maxtf;
    }

    public void setMaxtf(int maxtf) {
        this.maxtf = maxtf;
    }

    @Override
    public String toString() {
        return "Vocabulario{" + "palabra=" + palabra + ", nr=" + nr + ", maxtf=" + maxtf + '}';
    }
    
    
    
    
    
}
