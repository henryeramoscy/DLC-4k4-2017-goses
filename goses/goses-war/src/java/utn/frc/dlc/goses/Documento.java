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
public class Documento implements Serializable{
    private String nombre;
    private String url;

    public Documento() {
        super();
    }
    
    public Documento(String nombre, String url) {
        super();
        this.nombre = nombre;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Documento{" + "nombre=" + nombre + ", url=" + url + "}\n";
    }
    
    @Override
    public boolean equals(Object o)
    {
        boolean e = false;

        if (o != null && o instanceof Documento)
        {
           Documento d= (Documento) o;
           e=(d.getNombre().equals(this.getNombre()));
        }

        return e;
    }
        
}
