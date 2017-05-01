
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import utn.frc.dlc.goses.ParsingWord;
import utn.frc.dlc.goses.db.ConnectDB;
import utn.frc.dlc.goses.db.DBDocumento;
import utn.frc.dlc.goses.index.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pixelhar
 */
public class Main {
     public static void main(String[] args) throws IOException {
//       File f = new File(".\\");
//        String v[]=f.list();
//        System.out.println("faf"+v[1]);


//        ParsingWord ps= new ParsingWord("file\\cache\\0ddc809a.txt");
//        ps.readWordParsing();
//         System.out.println(ps.fileParsingString());
//         System.out.println(ps.getUrl());

//StringBuilder sb=new StringBuilder();
//        try (Scanner sc = new Scanner(f)) {
//            while (sc.hasNextLine()) {
//                sb.append(sc.nextLine()).append("\n");
//
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("No existe el archivo de entrada...");
//        }
//         System.out.println(sb.toString());
//long time_start, time_end;
//time_start = System.currentTimeMillis();


            
    	 
        

           
           
           
           


//        Indexacion i = new Indexacion("file\\cache\\gn06v10.txt");
//////         System.out.println(i.toStringP());
//        ConnectDB.setUpdateVocabulario();
//        time_end = System.currentTimeMillis();
//System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
 DBDocumento db = new DBDocumento("gn06v10.txt","");
         System.out.println(db.getIDDocumento());
        
    }
    
}
