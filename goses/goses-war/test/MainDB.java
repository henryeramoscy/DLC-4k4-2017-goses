
import java.io.File;
import java.sql.Connection;
import java.util.Hashtable;
import utn.frc.dlc.goses.db.ConnectDB;
//import utn.frc.dlc.goses.db.ConnectDB.getDBVocabularios;
import utn.frc.dlc.goses.db.DBDocumento;
import utn.frc.dlc.goses.index.Indexacion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pixelhar
 */
public class MainDB {

    public static void main(String[] args) {
//        Connection conection = ConnectDB.getConneection();
//DBDocumento doc= new DBDocumento("n","www.elcror.com/dad/faf/gag.txt");
//doc.setDateDocumento();
//int c=doc.getIDDocumento();
//        System.out.println(""+doc.toString());
//        System.out.println("id "+c);
//Hashtable<String,String> t=getDBVocabularios();
//
//        if(t!=null){
//        System.out.println(t.isEmpty());
//        }
        
        
        File afile = new File("file\\cache");
        String i[] = afile.list();
        for (int j = 0; j < i.length; j++) {
            try {
                
                afile = new File("file\\cache\\" + i[j]);
                Indexacion index = new Indexacion("file\\cache\\" + i[j]);
//                long time_start, time_end;
//                time_start = System.currentTimeMillis();
//                ConnectDB.setUpdateVocabulario();
//                time_end = System.currentTimeMillis();
//                System.out.println("the task has taken4 " + (time_end - time_start) + " milliseconds");
                if (afile.renameTo(new File("file\\share\\" + afile.getName()))) {
                    System.out.println(i[j] + " is moved successful!");
                } else {
                    System.out.println(i[j] + " is failed to move!");
                }
                
            
            } catch (Exception e) {
            }
        }
        
    }
}
