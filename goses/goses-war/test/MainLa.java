
import utn.frc.dlc.goses.buscador.Querry;
import utn.frc.dlc.goses.buscador.Search;
import utn.frc.dlc.goses.db.ConnectDB;

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
Search q=new Search("The want of a short and easy introduction to the study");
System.out.println(q.getRank().toString());

    }
}
