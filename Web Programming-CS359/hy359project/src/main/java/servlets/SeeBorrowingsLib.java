/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksTable;
import database.tables.EditBorrowingTable;
import database.tables.EditLibrarianTable;
import database.tables.GeneralQueries;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mainClasses.Book;
import mainClasses.Librarian;

/**
 *
 * @author plata
 */
@WebServlet(name = "SeeBorrowingsLib", urlPatterns = {"/SeeBorrowingsLib"})
public class SeeBorrowingsLib extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            SimpleDateFormat sdf;
            String username = session.getAttribute("loggedIn").toString();
            EditLibrarianTable est = new EditLibrarianTable();
            Librarian l = est.databaseToLibrarian2(username);
            EditBooksTable ebt = new EditBooksTable();
            EditBorrowingTable ee = new EditBorrowingTable();
            Book book;
            Date datea, dateb;
            //int uid = l.getUser_id();
            long timeDiff, daysDiff, date1, date2;
            GeneralQueries gq = new GeneralQueries();
           /* ArrayList<Borrowing> bact = gq.allOpenBorrowingsOfAStudent(uid);
            ArrayList<Borrowing> bend = gq.allEndedBorrowingsOfAStudent(uid);
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            // String isbn = "9780439064873";
            // bend.get(0).setIsbn(isbn);
            if (bact.size() != 0) {
                System.out.println("mphkabact");
                out.println("<div>");
                out.println("<h3><u>Active Borrowings</u></h3>");
                for (int i = 0; i < bact.size(); i++) {
                    book = ebt.databaseToBook(bact.get(i).getIsbn());
                    out.println((i + 1) + ". " + book.getTitle() + ": <br>");
                    out.println("  * Start: " + bact.get(i).getFromDate());
                    out.println("<br>");
                    out.println("  * End: " + bact.get(i).getToDate());
                    out.println("<br>");
                    sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    datea = sdf.parse(bact.get(i).getFromDate());
                    dateb = sdf.parse(bact.get(i).getToDate());
                    date1 = datea.getTime();
                    date2 = dateb.getTime();
                    timeDiff = Math.abs(date2 - date1);
                    daysDiff = (int) (timeDiff / (1000 * 60 * 60 * 24));
                    out.println("  * Days left: " + daysDiff);
                    out.println("<br>");
                    out.println("  * Status: " + bact.get(i).getStatus());
                    out.println("<br>");
                }
                out.println("</div>");
            }
            if (bend.size() != 0) {
                System.out.println("mphkabend");
                out.println("<div>");
                out.println("<h3><u>Ended Borrowings</u></h3>");
                for (int i = 0; i < bend.size(); i++) {
                    book = ebt.databaseToBook(bend.get(i).getIsbn());
                    out.println((i + 1) + ". " + book.getTitle() + ": <br>");
                    out.println("  * Start: " + bend.get(i).getFromDate());
                    out.println("<br>");
                    out.println("  * End: " + bend.get(i).getToDate());
                    out.println("<button class=\"button\" onclick='writerev(" + bend.get(i).getBorrowing_id() + ")'>Give a review and score</button>\n");
                    out.println("<br>");
                }
                out.println("</div>");
            }
            response.setStatus(200);
            if (bact.size() == 0 && bend.size() == 0) {
                System.out.println("mphka403");
                response.setStatus(403);
            }
       */ } catch (SQLException ex) {
            Logger.getLogger(SeeBorrowings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SeeBorrowings.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*catch (ParseException ex) {
            Logger.getLogger(SeeBorrowings.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

}
