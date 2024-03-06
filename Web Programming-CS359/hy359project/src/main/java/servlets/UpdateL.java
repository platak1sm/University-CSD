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
@WebServlet(name = "UpdateL", urlPatterns = {"/UpdateL"})
public class UpdateL extends HttpServlet {

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
        System.out.println("myusername= " + username);
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String address = request.getParameter("address");
        String personalpage = request.getParameter("personalpage");
        EditLibrarianTable eut = new EditLibrarianTable();
        PrintWriter out = response.getWriter();
        if (session.getAttribute("loggedIn") != null) {
            try {
                response.setStatus(200);
                Librarian l = eut.databaseToLibrarian2(username);
                if (password == "") {
                    password = l.getPassword();
                }

                if (email == "") {
                    email = l.getEmail();
                }
                if (telephone == "") {
                    telephone = l.getTelephone();
                }
                if (address == "") {
                    address = l.getAddress();
                }
                if (personalpage == "") {
                    personalpage = l.getPersonalpage();
                }
                System.out.println(password);
                System.out.println(email);
                eut.updateLibrarian(username, personalpage, password, email, address, telephone);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            response.setStatus(403);
        }
    }

}
