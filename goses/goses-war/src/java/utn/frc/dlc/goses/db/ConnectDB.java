/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import utn.frc.dlc.goses.Posteo;

/**
 *
 * @author pixelhar applicate sigleton patron
 */
public class ConnectDB {

    private final static String bd = "goses";
    private final static String login = "root";
    private final static String password = "38308351134hh";
    private final static String url = "jdbc:mysql://localhost:3306/" + bd;
    private final static boolean POOLCONECTION = false;

    public static Connection getConneection() {
        Connection conn = null;
        if (!POOLCONECTION) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, login, password);
                if (conn != null) {
                    System.out.println("Conectado a la base de datos [" + bd + "]");
                }
                return conn;

            } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
        } else {
          
            try {
                //en esta parte es donde ponemos el Nombre
                //de JNDI para que traiga el datasource
                DataSource ds = (DataSource) new InitialContext().lookup("jdbc/goses");
                conn = ds.getConnection();
            } catch (NamingException | SQLException ex) {
                System.err.println("Error:" + ex.getMessage());
            }
            return conn;
        }
        return null;
    }

    public static void setDataBTransaccional(LinkedList<Posteo> p, int idDoc) {
        Connection conn = ConnectDB.getConneection();
        Hashtable<String, String> t = getDBVocabularios();
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {

            conn.setAutoCommit(false);

            Iterator it = p.iterator();

            //PreparedStatement ps3;
            Posteo aux;
            while (it.hasNext()) {
                aux = (Posteo) it.next();
                if (!t.containsKey(aux.getVoc().getPalabra())) {
                    ps1 = conn.prepareStatement("INSERT INTO vocabulario (word)\n"
                            + "VALUES (?)");
                    ps1.setString(1, aux.getVoc().getPalabra());
                    ps1.executeUpdate();
                }

                ps2 = conn.prepareStatement("INSERT INTO posteo ( word, idDoc, tf)\n"
                        + "VALUES (?,?,?)");
                ps2.setString(1, aux.getVoc().getPalabra());
                ps2.setInt(2, idDoc);
                ps2.setInt(3, aux.getTf());
                ps2.executeUpdate();

            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
            if (conn != null) {
                System.out.println("Rollback");
                try {
                    //deshace todos los cambios realizados en los datos
                    conn.rollback();
                } catch (SQLException ex1) {
                    System.err.println("No se pudo deshacer" + ex1.getMessage());
                }
            }
        } finally {
            System.out.println("setDataBTransaccional ok");
            try {
                if (ps1 != null) {
                    ps1.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public static void setUpdateVocabulario() {
        Connection conn = ConnectDB.getConneection();
        PreparedStatement ps1 = null;
        try {

            conn.setAutoCommit(false);

            //PreparedStatement ps3;
            //Posteo aux;
            String word;
            int nr = 1, maxtf = 1;
            try {
                Statement stat = conn.createStatement();
                Statement stattf = conn.createStatement();
                Statement statmaxtf = conn.createStatement();
                ResultSet rstf = null, rsmaxtf = null;

                ResultSet rs = stat.executeQuery("SELECT word\n"
                        + "FROM vocabulario");
                while (rs.next()) {
                    word = rs.getString("word");
                    rstf = stattf.executeQuery("SELECT distinct count(idDoc) AS nr\n"
                            + "FROM posteo\n"
                            + "WHERE word =  '" + word + "';");
                    while (rstf.next()) {
                        nr = rstf.getInt("nr");
                        break;
                    }
                    //stattf.close();
                    rsmaxtf = statmaxtf.executeQuery("SELECT distinct MAX(tf) AS maxtf\n"
                            + "FROM posteo\n"
                            + "WHERE word =  '" + word + "';");
                    while (rsmaxtf.next()) {
                        maxtf = rsmaxtf.getInt("maxtf");
                        break;
                    }
                    //rsmaxtf.close();    
                    ps1 = conn.prepareStatement("UPDATE vocabulario\n"
                            + "SET nr = ?, maxtf = ?\n"
                            + "WHERE word = ?");
                    ps1.setInt(1, nr);
                    ps1.setInt(2, maxtf);
                    ps1.setString(3, word);
                    ps1.executeUpdate();

                }
                rstf.close();
                rsmaxtf.close();
                rs.close();

            } catch (SQLException ex) {
                System.out.println("Error " + ex.getMessage());
            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
            if (conn != null) {
                System.out.println("Rollback");
                try {
                    //deshace todos los cambios realizados en los datos
                    conn.rollback();
                } catch (SQLException ex1) {
                    System.err.println("No se pudo deshacer" + ex1.getMessage());
                }
            }
        } finally {
            System.out.println("setDataBTransaccional ok");
            try {
                if (ps1 != null) {
                    ps1.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    private static Hashtable<String, String> getDBVocabularios() {
        Hashtable<String, String> t = new Hashtable<>();
        Connection conn = ConnectDB.getConneection();
        Statement stat= null;
        try {
            stat = conn.createStatement();

            ResultSet rs = stat.executeQuery("SELECT word\n"
                    + "FROM vocabulario");
            while (rs.next()) {
                t.put(rs.getString("word"), rs.getString("word"));
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }finally {
               try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }
        return t;

    }
    
    public static DBVocabulario getVocabularioForSearch(String term){
    DBVocabulario vos= new DBVocabulario();
    Connection conn = ConnectDB.getConneection();
    Statement stat = null;
        try {
            stat = conn.createStatement();

            ResultSet rs = stat.executeQuery("SELECT *\n"
                    + "FROM vocabulario\n"
                    +"WHERE word = '"+term +"'");
            while (rs.next()) {
                vos =new DBVocabulario(rs.getString("word"),rs.getInt("nr"),rs.getInt("maxtf"));
                break;
            }
            rs.close();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        finally {
               try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }
        return vos;
    }
    
    public static ArrayList<DBDocumento> getDocumentosOfTerm(String term){
        ArrayList<DBDocumento> docs=new ArrayList<>();
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            PreparedStatement pS2 = null;
            ResultSet rsIdoc,rsDoc = null;
            conn = ConnectDB.getConneection();  
        try {          
                 
            String selectSQL = "SELECT idDoc\n" +
                               "FROM posteo\n" +
                               "WHERE word = ?\n"+ 
                               "ORDER BY tf DESC;";
            preparedStatement = conn.prepareStatement(selectSQL);
            preparedStatement.setString(1, term);
            
            // execute select SQL stetement
            rsIdoc = preparedStatement.executeQuery();
            
            while (rsIdoc.next()) {
               String q = "SELECT *\n" +
                          "FROM documentos\n" +
                          "WHERE idDoc = ?;";
            pS2 = conn.prepareStatement(q);
            pS2.setInt(1, rsIdoc.getInt("idDoc"));
            rsDoc = pS2.executeQuery();
            
            while (rsDoc.next()) {
            docs.add(new DBDocumento(rsDoc.getInt("idDoc"),rsDoc.getString("nombre"),rsDoc.getString("url")));
            rsDoc.close();
            break;
            }  
            }
            
            rsIdoc.close(); 
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage()); 
        }finally {
               try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (pS2 != null) {
                    pS2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }
        return docs;
        
        
    }

}
