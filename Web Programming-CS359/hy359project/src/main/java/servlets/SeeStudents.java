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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Student;

/**
 *
 * @author plata
 */
@WebServlet(name = "SeeStudents", urlPatterns = {"/SeeStudents"})
public class SeeStudents extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EditStudentsTable elt = new EditStudentsTable();
        String username, fname, lname;
        try {
            ArrayList<Student> s = elt.databaseToStudents();
            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<u>Students List</u>");
                for (int i = 0; i < s.size(); i++) {
                    username = s.get(i).getUsername();
                    fname = s.get(i).getFirstname();
                    lname = s.get(i).getLastname();
                    out.println("<div>");
                    out.println((i + 1) + ". Username: " + username + " First Name: " + fname + " Last Name: " + lname);
                    out.println("<br>");
                    out.println("<button class=\"button\" onclick='del(\"" + username + "\")'>Delete</button>\n");
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
