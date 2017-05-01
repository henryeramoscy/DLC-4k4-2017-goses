/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses.index;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import utn.frc.dlc.goses.ParsingWord;
import utn.frc.dlc.goses.Posteo;
import utn.frc.dlc.goses.db.ConnectDB;
import utn.frc.dlc.goses.db.DBDocumento;

/**
 *
 * @author pixelhar
 */
public class Indexacion {
    private ParsingWord pw;
    private Hashtable<String,Posteo> wordht;
    //private ConnectDB db;
    private int idDoc;
    private LinkedList<Posteo> posteos; 
//    private SimpleList words;
    long time_start, time_end;

    public Indexacion(String dir) {
        pw = new ParsingWord(dir);
        pw.readWordParsing();
        wordht=new Hashtable<>();
        posteos=new LinkedList<>();
//        words=new SimpleList();
//        db=new ConnectDB();
        time_start = System.currentTimeMillis();
        DBDocumento dbDoc=new DBDocumento(pw.getNombreDoc(),pw.getUrl());
        dbDoc.setDateDocumento();
        time_end = System.currentTimeMillis();
        System.out.println("the task has taken1 " + (time_end - time_start) + " milliseconds");
        time_start = System.currentTimeMillis();
        idDoc=dbDoc.getIDDocumento();
        time_end = System.currentTimeMillis();
        System.out.println("the task has taken2 " + (time_end - time_start) + " milliseconds");
        coutTermDoc();
        time_start = System.currentTimeMillis();
        ConnectDB.setDataBTransaccional(posteos,idDoc);
        time_end = System.currentTimeMillis();
        System.out.println("the task has taken3 " + (time_end - time_start) + " milliseconds");
        time_start = System.currentTimeMillis();
        ConnectDB.setUpdateVocabulario();
        time_end = System.currentTimeMillis();
        System.out.println("the task has taken4 " + (time_end - time_start) + " milliseconds");
//        addDB();
//        db.closeDB();
    }
    private void coutTermDoc() {
        Posteo p, aux;
        Iterator it = pw.getPosteos().iterator();
        while (it.hasNext()) {
            p = (Posteo) it.next();
            if (wordht.containsKey(p.getVoc().getPalabra())) {
                aux = (Posteo) wordht.get(p.getVoc().getPalabra());
                aux.countTf();
                //db.setUpDateDataPalabra(aux.getPalabra(), aux.getCount(), idDoc);
            } else {
                wordht.put(p.getVoc().getPalabra(), p);
                posteos.add(p);
                //db.setDataPalabra(p.getPalabra(), p.getCount(), idDoc);
            }
        }
    }
    
//    private void addDB(){
//        Palabra aux;
//        Iterator it =words.iterator();
//        while (it.hasNext()) {
//            Palabra p = (Palabra) it.next();
//            if (ht.contains(p)) {
//                aux=(Palabra) ht.get(p);
//                db.getDatosIn().addInOrder(aux);
//            }
//        }
//        db.setDataPalabraTransaccional(idDoc);
//    }
//    
   
    public String toStringP(){
        return posteos.toString()+"\n";
    }
    @Override
    public String toString(){
        return wordht.toString()+"\n";
    }


    
}
