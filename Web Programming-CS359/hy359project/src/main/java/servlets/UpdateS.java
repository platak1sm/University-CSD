/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
import mainClasses.Student;

/**
 *
 * @author plata
 */
@WebServlet(name = "UpdateS", urlPatterns = {"/UpdateS"})
public class UpdateS extends HttpServlet {

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
        EditStudentsTable eut = new EditStudentsTable();
        PrintWriter out = response.getWriter();
        if (session.getAttribute("loggedIn") != null) {
            try {
                response.setStatus(200);
                Student su = eut.databaseToStudent3(username);
                if (password == "") {
                    password = su.getPassword();
                }

                if (email == "") {
                    email = su.getEmail();
                }
                if (telephone == "") {
                    telephone = su.getTelephone();
                }
                if (address == "") {
                    address = su.getAddress();
                }
                if (personalpage == "") {
                    personalpage = su.getPersonalpage();
                }
                System.out.println(password);
                System.out.println(email);
                eut.updateStudent(username, personalpage, password, email, address, telephone);
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
