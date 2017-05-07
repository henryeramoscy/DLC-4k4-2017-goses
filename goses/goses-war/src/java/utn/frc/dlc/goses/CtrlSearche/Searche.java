/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.goses.CtrlSearche;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utn.frc.dlc.goses.Documento;
import utn.frc.dlc.goses.buscador.DocumentRank;
import utn.frc.dlc.goses.buscador.Search;

/**
 *
 * @author pixelhar
 */
public class Searche extends HttpServlet {
    private static ArrayList<DocumentRank> a=new ArrayList() ;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Searche</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Searche at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html");
//
//      PrintWriter out = response.getWriter();
//      String title = "Using GET Method to Read Form Data";
//		
//      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " +
//         "transitional//en\">\n";
//			
//      out.println(docType + "<html>\n" +
//         "<head><title>" + title + "</title></head>\n" +
//         "<body bgcolor=\"#f0f0f0\">\n" +
//         "<h1 align=\"center\">" + title + "</h1>\n" +
//         "<ul>\n" +
//         "  <li><b>gosesearch</b>: "
//         + request.getParameter("gosesearch") + "\n"  +
//         "</ul>\n" +
//         "</body></html>");

        //ErrorMsg errorMsg = null;
        String errorTitle = "No se pudo cargar los alumnos";
        String dest = "/error.jsp";
        Documento doc = null;
        
        //DBManager db = null;

        try {
            //----------------------------------------
            if (request.getParameter("gosesearch") != null){ 
                
                Search q=new Search(request.getParameter("gosesearch"));
                a=q.getRank();
//                doc =new Documento("dada",request.getParameter("gosesearch"));
//                a.add(doc);
            
            }
            

            request.setAttribute("documentos", a);
            dest = "/documentos.jsp";

        } catch (Exception e) {
            //errorMsg = new ErrorMsg(errorTitle, e.getMessage());
            //request.setAttribute("errorMsg", errorMsg);
        } finally {
            //if (db != null) db.close();
        }

        ServletContext app = this.getServletContext();
        RequestDispatcher disp = app.getRequestDispatcher(dest);
        disp.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
