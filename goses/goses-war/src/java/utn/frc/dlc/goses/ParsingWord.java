/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
/**
 *
 * @author PixelHar
 */
public class ParsingWord
 {
    private final  String nombreDoc;
    private final  File f;
    private final  String url;
    private LinkedList<Posteo> posteos;

    public ParsingWord(String dir) {
        f=new File(dir);
        nombreDoc=f.getName();
        url="file\\share\\"+f.getName();
        posteos=new LinkedList();
    }

    public String getUrl() {
        return url;
    }
    
    public void readWordParsing(){
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String num = sc.next();
                String delims = "[ .,?!--;://@_!¡«»?¿0123456789=°ªï»¿]+";
                String[] tokens=num.split(delims);
                for (String token : tokens) {
                    if (!token.isEmpty() && token != null) {
                        posteos.addLast(new Posteo(new Termino(remove(token).toLowerCase().trim()),new Documento()));   
                    }
                }
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe el archivo de entrada...");
        }
    }
    
    public String readFile() {
        StringBuilder sb=new StringBuilder();
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine()).append("\n");

            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe el archivo de entrada...");
        }
        return sb.toString();
    }
    public String getNombreDoc() {
        return nombreDoc;
    }

    public LinkedList getPosteos() {
        return posteos;
    }
    
    
    private String remove(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ[]\\<>`{}|~'^";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy            ";
        String output = input;
        for (int i=0; i<original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }//for i
        return output;
    }
    
    public String fileParsingString(){
        return posteos.toString();
    }
    
}
