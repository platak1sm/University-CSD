/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditLibrarianTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mainClasses.JSONConverter;
import mainClasses.Librarian;

/**
 *
 * @author plata
 */
@WebServlet(name = "LoginLib", urlPatterns = {"/LoginLib"})
public class LoginLib extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        EditLibrarianTable eut = new EditLibrarianTable();
        String username = session.getAttribute("loggedIn").toString();
        if (session.getAttribute("loggedIn") != null) {
            response.setStatus(200);
            try {
                Librarian l = eut.databaseToLibrarian2(username);
                response.getWriter().write(username);

            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.setStatus(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            JSONConverter jc = new JSONConverter();
            EditLibrarianTable elt = new EditLibrarianTable();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println("username =" + username);
            System.out.println("pw=" + password);
            Librarian l2 = elt.databaseToLibrarian(username, password);
            if (l2 != null) {
                session.setAttribute("loggedIn", l2.getUsername());
                int activeUsers = 0;
                if (request.getServletContext().getAttribute("activeUsers") != null) {
                    activeUsers = (int) request.getServletContext().getAttribute("activeUsers");
                }
                request.getServletContext().setAttribute("activeUsers", activeUsers + 1);
                response.setStatus(200);

            } else {
                response.setStatus(403);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
