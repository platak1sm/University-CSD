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
@WebServlet(name = "SportsS", urlPatterns = {"/SportsS"})
public class SportsS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EditBooksTable eb = new EditBooksTable();
        String img, name, isbn;
        try {
            ArrayList<Book> booksall = eb.databaseToBooks("Sports");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println(" <div class=\"topnav\">\n"
                        + "  <button class=\"button\" onclick=\"showAllS();\">All</button>\n"
                        + "  <button class=\"button\" onclick=\"showFS();\">Fantasy</button>\n"
                        + "  <button class=\"button\" onclick=\"showRS();\">Romance</button>\n"
                        + "  <button class=\"button\" onclick=\"showNS();\">Novel</button>\n"
                        + "  <button class=\"button\" onclick=\"showSS();\">Sports</button>\n"
                        + "  <button class=\"button\" onclick=\"showAS();\">Adventure</button>"
                        + "</div> ");
                for (int i = 0; i < booksall.size(); i++) {
                    img = booksall.get(i).getPhoto();
                    name = booksall.get(i).getTitle();
                    isbn = booksall.get(i).getIsbn();
                    out.println("<div>");
                    out.println("<img src=\"" + img + " \" alt=\"Book" + i + "\"");
                    out.println("<br>");
                    out.println(name);
                    out.println("<button class=\"button\" onclick='showav(" + isbn + ")'>Show availability</button>\n");
                    out.println("<button class=\"button\" onclick='showmore(" + isbn + ")'>Show more</button>\n");
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
