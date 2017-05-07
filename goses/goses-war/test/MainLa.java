
import utn.frc.dlc.goses.buscador.Search;
import utn.frc.dlc.goses.db.ConnectDB;
import utn.frc.dlc.goses.db.DBVocabulario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pixelhar
 */
public class MainLa {
    public static void main(String[] args) {
Search q=new Search("The Myth of Osiris");
     System.out.println(q.getRank().toString());
//System.out.println(q.getRank().toString());
//System.out.println("cantidad:"+ConnectDB.getCantDocumentos());
//DBVocabulario voc=new DBVocabulario("the",1,1);
//        System.out.println(voc.getCantTermEnDoc(2));

    }
}
