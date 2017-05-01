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
public class DBPosteo implements Serializable{
    private DBVocabulario voc;
    private DBDocumento doc;
    private int tf;
    
    public DBPosteo() {
        super();
    }
    
    public DBPosteo(DBVocabulario voc, DBDocumento doc, int tf) {
        super();
        this.voc = voc;
        this.doc = doc;
        this.tf = tf;
    }

    public DBVocabulario getVoc() {
        return voc;
    }

    public void setVoc(DBVocabulario voc) {
        this.voc = voc;
    }

    public DBDocumento getDoc() {
        return doc;
    }

    public void setDoc(DBDocumento doc) {
        this.doc = doc;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    @Override
    public String toString() {
        return "Posteo{" + "voc=" + voc + ", doc=" + doc + ", tf=" + tf + '}';
    }
    
    
    
    
     
    
    
}
