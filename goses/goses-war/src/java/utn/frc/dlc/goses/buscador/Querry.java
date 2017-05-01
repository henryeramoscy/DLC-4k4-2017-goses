/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses.buscador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utn.frc.dlc.goses.db.ConnectDB;
import utn.frc.dlc.goses.db.DBVocabulario;

/**
 *
 * @author pixelhar
 */
public class Querry {

    private String querry;
    private ArrayList<DBVocabulario> wq;

    public Querry(String querry) {
        this.querry = querry;
        wq=new ArrayList<>();
        parsingQuerry();
        addOrderNr();
        
    }
    

    private void parsingQuerry() {
        String q = querry;
        String delims = "[ .,?!--;://@_!¡«»?¿0123456789=°ªï»¿]+";
        String[] tokens = q.split(delims);
        for (String token : tokens) {
            if (!token.isEmpty() && token != null) {
                wq.add(ConnectDB.getVocabularioForSearch(remove(token).toLowerCase().trim()));
            }
        }

    }

    private String remove(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ[]\\<>`{}|~'^";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy            ";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }
    
    private void addOrderNr(){
        Collections.sort(wq, (DBVocabulario v1, DBVocabulario v2) -> new Integer(v1.getNr()).compareTo(v2.getNr()));
    
    }

    public ArrayList<DBVocabulario> getWq() {
        return wq;
    }
    

    @Override
    public String toString() {
        return wq.toString();
    }
    
}
