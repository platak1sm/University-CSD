/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditLibrarianTable;
import database.tables.EditStudentsTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Librarian;
import mainClasses.Student;

/**
 *
 * @author plata
 */
@WebServlet(name = "DelUser", urlPatterns = {"/DelUser"})
public class DelUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        EditStudentsTable est = new EditStudentsTable();
        EditLibrarianTable elt = new EditLibrarianTable();
        try {
            Librarian l = elt.databaseToLibrarian2(username);
            Student s = est.databaseToStudent3(username);
            if (l == null & s == null) {
                response.setStatus(403);
            } else if (l != null) {
                elt.deleteLibrarian(username);
            } else if (s != null) {
                est.deleteStudent(username);
            }
            response.setStatus(200);
        } catch (SQLException ex) {
            Logger.getLogger(DelUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DelUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
