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
public class Posteo implements Serializable{
    private Termino voc;
    private Documento doc;
    private int tf;
    
    public Posteo() {
    }
    
    public Posteo(Termino voc, Documento doc) {
        this.voc = voc;
        this.doc = doc;
        this.tf = 1;
    }

    public Termino getVoc() {
        return voc;
    }

    public void setVoc(Termino voc) {
        this.voc = voc;
    }

    public Documento getDoc() {
        return doc;
    }

    public void setDoc(Documento doc) {
        this.doc = doc;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }
    
    public void countTf(){
        tf++;
    }

    @Override
    public String toString() {
        return "Posteo{" + "term=" + voc + ", doc=" + doc + ", tf=" + tf + "}\n";
    }
    
   
}
