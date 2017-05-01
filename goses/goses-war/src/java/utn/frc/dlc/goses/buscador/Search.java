/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses.buscador;

import java.util.ArrayList;
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

    private ArrayList<Documento> rank;
    private final Querry q;

    public Search(String querry) {
        q = new Querry(querry);
        rank=new ArrayList<>();
        gernerarRank();
        

    }

    private void gernerarRank() {
        ArrayList<DBVocabulario> vocabs = q.getWq();
        ArrayList<DBDocumento> docs;
        Iterator<DBVocabulario> v = vocabs.iterator();
        DBDocumento aux;
        while (v.hasNext()) {
            docs = ConnectDB.getDocumentosOfTerm(v.next().getPalabra());
            Iterator<DBDocumento> d = docs.iterator();
            while (d.hasNext()) {
                aux=d.next();
                if(!rank.contains(new Documento(aux.getNombre(),aux.getUrl()))){
                    rank.add(new Documento(aux.getNombre(),aux.getUrl()));
                }
            }
        }
    }

    public ArrayList<Documento> getRank() {
        return rank;
    }
    
    

}
