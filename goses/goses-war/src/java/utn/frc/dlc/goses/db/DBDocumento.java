/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pixelhar
 */
public class DBDocumento implements Serializable{
    private int idDoc;
    private String nombre;
    private String url;
    private Connection conn=ConnectDB.getConneection();

    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }
   
    public DBDocumento() {
        super();
    }
    
    public DBDocumento(String nombre, String url) {
        super();
        this.nombre = nombre;
        this.url = url;
    }
    public DBDocumento(int id,String nombre, String url) {
        this(nombre,url);
        idDoc=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Documento{" + "nombre=" + nombre + ", url=" + url + "}\n";
    }
    
    public void setDateDocumento() {
        PreparedStatement ps = null;
        try {

            //Statement stat = conn.createStatement();
            
             ps = conn.prepareStatement("INSERT INTO documentos (nombre,url)\n"
                    + "VALUES ( ?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, url);
            
            ps.executeUpdate();

            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        finally {
               try {
                   if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }

    }
     public int getIDDocumento(){
         int id = 0;
         Statement stat = null;
        try {
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT idDoc\n"
                    + "FROM documentos\n"
                    + "WHERE nombre='"+nombre+"'");
            while (rs.next()) {
                id=rs.getInt("idDoc");
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }finally {
               try {
                   if(stat!=null){
                       stat.close();
                   }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }
        return id;
    }
    
    
    
    
    
    
    
}
