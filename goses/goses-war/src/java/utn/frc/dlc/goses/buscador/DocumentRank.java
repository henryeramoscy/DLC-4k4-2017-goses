/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses.buscador;

import java.util.Objects;
import utn.frc.dlc.goses.Documento;

/**
 *
 * @author pixelhar
 */
public class DocumentRank {
    
    private int numero;
    private double rank;
    private String nombre;
    private String url;

    public DocumentRank(int numero, double rank, String nombre, String url) {
        this.numero = numero;
        this.rank = rank;
        this.nombre = nombre;
        this.url = url;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
    public void upRank(){
        rank+=1;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        boolean e = false;
        if (o != null && o instanceof DocumentRank)
        {
           DocumentRank dr= (DocumentRank) o;
           e=(dr.numero==this.numero);
        }
        return e;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "DocumentRank{" + "numero=" + numero + ", rank=" + rank + ", nombre=" + nombre + ", url=" + url + "}\n";
    }


    
    
    
}
