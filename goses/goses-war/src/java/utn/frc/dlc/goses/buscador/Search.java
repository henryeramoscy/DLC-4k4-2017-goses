/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses.buscador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import utn.frc.dlc.goses.Documento;
import utn.frc.dlc.goses.db.ConnectDB;
import utn.frc.dlc.goses.db.DBDocumento;
import utn.frc.dlc.goses.db.DBVocabulario;

/**
 *
 * @author pixelhar
 */
public class Search {

    private ArrayList<DocumentRank> rank;
    private final Query q;

    public Search(String querry) {
        q = new Query(querry);
        rank=new ArrayList<>();
        gernerarRank();
        addOrderRank();
        

    }

    private void gernerarRank() {
        double N = ConnectDB.getCantDocumentos();
        ArrayList<DBVocabulario> vocabs = q.getWq();
        ArrayList<DBDocumento> docs;
        Hashtable<String,DocumentRank> auxDr=new Hashtable<>();
        Iterator<DBVocabulario> v = vocabs.iterator();
        DBDocumento auxDoc;
        DBVocabulario auxVoc;
        while (v.hasNext()) {
            auxVoc = v.next();
            docs = ConnectDB.getDocumentosOfTerm(auxVoc.getPalabra());
            Iterator<DBDocumento> d = docs.iterator();
            while (d.hasNext()) {
                auxDoc = d.next();
                if (!rank.contains(new DocumentRank(auxDoc.getIdDoc(),0f,auxDoc.getNombre(),auxDoc.getUrl()) )) {
                    double log = Math.log10(((double) N / (double) auxVoc.getNr()));
                    double valorRanking = (double)auxVoc.getCantTermEnDoc(auxDoc.getIdDoc()) * log;
                    DocumentRank dr=new DocumentRank(auxDoc.getIdDoc(), valorRanking,auxDoc.getNombre(), auxDoc.getUrl());
                    rank.add(dr);
                    auxDr.put(auxDoc.getNombre(), dr);
                } else {
                    auxDr.get(auxDoc.getNombre()).upRank();
                }

            }
        }
    }

    public ArrayList<DocumentRank> getRank() {
        return rank;
    }
    
    private void addOrderRank(){
        Collections.sort(rank, (DocumentRank d1, DocumentRank d2) -> d2.getRank().compareTo(d1.getRank())
        );
    
    }

}
