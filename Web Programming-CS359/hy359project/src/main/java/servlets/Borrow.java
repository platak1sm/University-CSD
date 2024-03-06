/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksInLibraryTable;
import database.tables.EditBorrowingTable;
import database.tables.EditStudentsTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mainClasses.BookInLibrary;
import mainClasses.Borrowing;
import mainClasses.Student;

/**
 *
 * @author plata
 */
@WebServlet(name = "Borrow", urlPatterns = {"/Borrow"})
public class Borrow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int uid;
        try {
            String isbn = request.getParameter("isbn");
            int lid = Integer.parseInt(request.getParameter("library_id"));
            EditBooksInLibraryTable ebil = new EditBooksInLibraryTable();
            BookInLibrary book = ebil.databaseToBookInLibrary3(isbn, lid);

            if (book != null) {
                EditBorrowingTable ebt = new EditBorrowingTable();
                Borrowing b = new Borrowing();
                b.setBookcopy_id(book.getBookcopy_id());
                HttpSession session = request.getSession();
                EditStudentsTable eut = new EditStudentsTable();
                String username = session.getAttribute("loggedIn").toString();
                Student su = eut.databaseToStudent3(username);
                uid = su.getUser_id();
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<h2><u> Borrowing </u></h2>");
                out.println("<br>");
                out.println("<h4>Request for borrowing sent to Librarian of Library " + lid + ". </h4>");
                book.setAvailable("false");
                b.setUser_id(uid);
                b.setBookcopy_id(book.getBookcopy_id());
                b.setFromDate(java.time.LocalDate.now().toString());
                b.setToDate(java.time.LocalDate.now().plusMonths(1).toString());
                b.setStatus("requested");
                // b.setIsbn(isbn);
                ebt.createNewBorrowing(b);
                response.setStatus(200);
            } else {
                response.setStatus(403);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Borrow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Borrow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
