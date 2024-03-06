/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Book;

/**
 *
 * @author plata
 */
@WebServlet(name = "RomanceG", urlPatterns = {"/RomanceG"})
public class RomanceG extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
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
        EditBooksTable eb = new EditBooksTable();
        String img, name, url;
        try {
            ArrayList<Book> booksall = eb.databaseToBooks("Romance");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println(" <div class=\"topnav\">\n"
                        + "  <button class=\"button\" onclick=\"showAllG();\">All</button>\n"
                        + "  <button class=\"button\" onclick=\"showFG();\">Fantasy</button>\n"
                        + "  <button class=\"button\" onclick=\"showRG();\">Romance</button>\n"
                        + "  <button class=\"button\" onclick=\"showNG();\">Novel</button>\n"
                        + "  <button class=\"button\" onclick=\"showSG();\">Sports</button>\n"
                        + "  <button class=\"button\" onclick=\"showAG();\">Adventure</button>"
                        + "</div> ");
                for (int i = 0; i < booksall.size(); i++) {
                    img = booksall.get(i).getPhoto();
                    name = booksall.get(i).getTitle();
                    url = booksall.get(i).getUrl();
                    out.println("<div>");
                    out.println("<img src=\"" + img + " \" alt=\"Book" + i + "\"");
                    out.println("<br>");
                    out.println("<a href=\"" + url + "\">" + name + "</a>");
                    out.println("</div>");
                }
                response.setStatus(200);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Guest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
