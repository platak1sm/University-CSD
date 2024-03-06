/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditLibrarianTable;
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
import mainClasses.Librarian;

/**
 *
 * @author plata
 */
@WebServlet(name = "SeeLibs", urlPatterns = {"/SeeLibs"})
public class SeeLibs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EditLibrarianTable elt = new EditLibrarianTable();
        String username, fname, lname;
        try {
            ArrayList<Librarian> ls = elt.databaseToLibrarians();
            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<u>Librarians List</u>");
                for (int i = 0; i < ls.size(); i++) {
                    username = ls.get(i).getUsername();
                    fname = ls.get(i).getFirstname();
                    lname = ls.get(i).getLastname();
                    out.println("<div>");
                    out.println((i + 1) + ". Username: " + username + " First Name: " + fname + " Last Name: " + lname);
                    out.println("<br>");
                    out.println("<button class=\"button\" onclick='del(" + username + ")'>Delete</button>\n");
                    out.println("<br>");
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
