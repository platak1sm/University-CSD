/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksTable;
import database.tables.EditLibrarianTable;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddBook", urlPatterns = {"/AddBook"})
public class AddBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String authors = request.getParameter("authors");
        String genre = request.getParameter("genre");
        int pages = Integer.parseInt(request.getParameter("pages"));
        int pyear = Integer.parseInt(request.getParameter("publicationyear"));
        String url = request.getParameter("url");
        String photo = request.getParameter("photo");
        EditLibrarianTable eut = new EditLibrarianTable();
        EditBooksTable ebt = new EditBooksTable();
        PrintWriter out = response.getWriter();
        Book b = new Book();
        b.setIsbn(isbn);
        b.setAuthors(authors);
        b.setTitle(title);
        b.setGenre(genre);
        b.setPages(pages);
        b.setPublicationyear(pyear);
        b.setPhoto(photo);
        b.setUrl(url);
        if (b != null) {
            try {
                ebt.createNewBook(b);
                response.setStatus(200);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.setStatus(403);
        }
    }

}
