/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksInLibraryTable;
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
import mainClasses.BookInLibrary;

/**
 *
 * @author plata
 */
@WebServlet(name = "ShowAvailability", urlPatterns = {"/ShowAvailability"})
public class ShowAvailability extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String isbn = request.getParameter("isbn");
            System.out.println(isbn + " swpaa");
            EditBooksInLibraryTable ebil = new EditBooksInLibraryTable();
            EditBooksTable ebt = new EditBooksTable();
            ArrayList<BookInLibrary> books = ebil.databaseToBooksInLibrary(isbn);
            Book book1 = ebt.databaseToBook(isbn);
            int bid, lid, i;
            String av, img = book1.getPhoto();

            if (books != null) {
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<h2><u> Availability </u></h2>");
                out.println("<div>");
                out.println("<img src=\"" + img + " \" alt=\"Book\"");
                out.println("<br>");
                out.println(book1.getTitle());
                out.println("</div>");
                out.println("<br>");
                System.out.println(books.size());
                for (i = 0; i < books.size(); i++) {
                    System.out.println(" swpaaresy");
                    lid = books.get(i).getLibrary_id();
                    bid = books.get(i).getBookcopy_id();
                    out.println("Library " + lid);
                    out.println("<button class=\"button\" onclick='borrowfrom(" + lid + ", " + isbn + ")'>Borrow</button>\n");
                    out.println("<br>");
                }
                response.setStatus(200);

            } else {
                response.setStatus(403);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ShowAvailability.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShowAvailability.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
