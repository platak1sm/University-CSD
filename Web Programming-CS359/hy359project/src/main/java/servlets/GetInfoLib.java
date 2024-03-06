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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mainClasses.Librarian;

/**
 *
 * @author plata
 */
@WebServlet(name = "GetInfoLib", urlPatterns = {"/GetInfoLib"})
public class GetInfoLib extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = session.getAttribute("loggedIn").toString();
        EditLibrarianTable eut = new EditLibrarianTable();
        PrintWriter out = response.getWriter();
        if (session.getAttribute("loggedIn") != null) {
            try {
                response.setStatus(200);
                Librarian l = eut.databaseToLibrarian2(username);
                out.println("<!DOCTYPE html>");
                out.println("<h1><u>Your Data</u></h1><br>");
                out.println("Username: " + username);
                out.println("<br>");
                out.println("Password: " + l.getPassword());
                out.println("<br>");
                out.println("Email: " + l.getEmail());
                out.println("<br>");
                out.println("First Name: " + l.getFirstname());
                out.println("<br>");
                out.println("Last Name: " + l.getLastname());
                out.println("<br>");
                out.println("Gender: " + l.getGender());
                out.println("<br>");
                out.println("Library name: " + l.getLibraryname());
                out.println("<br>");
                out.println("Library info: " + l.getLibraryinfo());
                out.println("<br>");
                out.println("Telephone: " + l.getTelephone());
                out.println("<br>");
                out.println("Country: " + l.getCountry());
                out.println("<br>");
                out.println("City: " + l.getCity());
                out.println("<br>");
                out.println("Address: " + l.getAddress());
                out.println("<br>");
                out.println("Personal Page: " + l.getPersonalpage());
                out.println("<br>");
            } catch (SQLException ex) {
                Logger.getLogger(GetInfoLib.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetInfoLib.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            response.setStatus(403);
        }
    }

}
