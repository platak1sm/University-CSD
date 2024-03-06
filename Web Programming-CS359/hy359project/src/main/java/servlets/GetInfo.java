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
@WebServlet(name = "GetInfo", urlPatterns = {"/GetInfo"})
public class GetInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = session.getAttribute("loggedIn").toString();
        EditStudentsTable eut = new EditStudentsTable();
        PrintWriter out = response.getWriter();
        if (session.getAttribute("loggedIn") != null) {
            try {
                response.setStatus(200);
                Student su = eut.databaseToStudent3(username);
                out.println("<!DOCTYPE html>");
                out.println("<h1><u>Your Data</u></h1><br>");
                out.println("Username: " + username);
                out.println("<br>");
                out.println("Password: " + su.getPassword());
                out.println("<br>");
                out.println("Email: " + su.getEmail());
                out.println("<br>");
                out.println("First Name: " + su.getFirstname());
                out.println("<br>");
                out.println("Last Name: " + su.getLastname());
                out.println("<br>");
                out.println("Gender: " + su.getGender());
                out.println("<br>");
                out.println("University: " + su.getUniversity());
                out.println("<br>");
                out.println("Department: " + su.getDepartment());
                out.println("<br>");
                out.println("Student ID: " + su.getStudent_id());
                out.println("<br>");
                out.println("Country: " + su.getCountry());
                out.println("<br>");
                out.println("City: " + su.getCity());
                out.println("<br>");
                out.println("Address: " + su.getAddress());
                out.println("<br>");
                out.println("Telephone: " + su.getTelephone());
                out.println("<br>");
                out.println("Personal Page: " + su.getPersonalpage());
                out.println("<br>");
            } catch (SQLException ex) {
                Logger.getLogger(GetInfo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            response.setStatus(403);
        }
    }

}
