/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksTable;
import database.tables.EditReviewsTable;
import database.tables.EditStudentsTable;
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
import mainClasses.Review;
import mainClasses.Student;

/**
 *
 * @author plata
 */
@WebServlet(name = "ShowMore", urlPatterns = {"/ShowMore"})
public class ShowMore extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String isbn = request.getParameter("isbn");
            EditBooksTable ebt = new EditBooksTable();
            EditStudentsTable est= new EditStudentsTable();
            Book book = ebt.databaseToBook(isbn);
            Student su;
            String username;
            int pages, pyear;
            String title, authors, genre, url, img = book.getPhoto();
            EditReviewsTable ert= new EditReviewsTable();
            ArrayList<Review> reviews= ert.databaseToReviews(isbn);
            if (book != null) {
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<h2><u> Info </u></h2>");
                out.println("<div>");
                out.println("<img src=\"" + img + " \" alt=\"Book\"");
                out.println("<br>");
                out.println("<br>");
                out.println("Title: " + book.getTitle());
                out.println("<br>");
                out.println("Authors: " + book.getAuthors());
                out.println("<br>");
                out.println("Genre: " + book.getGenre());
                out.println("<br>");
                out.println("Pages: " + book.getPages());
                out.println("<br>");
                out.println("Publication Year: " + book.getPublicationyear());
                out.println("<br>");
                out.println("URL: <a href=\"" + book.getUrl() + "\">Click here</a>");
                out.println("</div>");
                out.println("<br>");
                out.println("<br>");
                if (reviews.size() != 0) {
                    out.println("<h4><u>Reviews</u></h4>");
                    for (int i=0; i<reviews.size(); i++){
                        su = est.databaseToStudent4(reviews.get(i).getUser_id());
                        username = su.getUsername();
                        out.println((i + 1) + ". " + username + ": " + reviews.get(i).getReviewText() + "   Score: " + reviews.get(i).getReviewScore());
                        out.println("<br>");
                    }
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
