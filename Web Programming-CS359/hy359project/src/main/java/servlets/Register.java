/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.tables.EditLibrarianTable;
import database.tables.EditStudentsTable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.JSONConverter;
import mainClasses.Librarian;
import mainClasses.Student;

/**
 *
 * @author plata
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        try {
            JSONConverter jc = new JSONConverter();
            BufferedReader inputJSONfromClient = request.getReader();
            EditStudentsTable eut = new EditStudentsTable();
            EditLibrarianTable elt = new EditLibrarianTable();
            String json1 = inputJSONfromClient.lines().collect(Collectors.joining());
            Student su = eut.jsonToStudent(json1);
            Student su2 = eut.databaseToStudent2(su.getUsername(), su.getEmail(), su.getStudent_id());
            Librarian l = elt.jsonToLibrarian(json1);
            Librarian l2 = elt.databaseToLibrarian3(l.getUsername(), l.getEmail());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            if (su2 != null || l2 != null) {
                response.setStatus(409);
                Gson gson = new Gson();
                JsonObject jo = new JsonObject();
                jo.addProperty("error", "Username, Email or Student ID number Already Taken");
                response.getWriter().write(jo.toString());
            } else {
                response.setStatus(200);
                if (l != null) {
                    l.setLat(1.0);
                    l.setLon(1.0);
                    elt.addNewLibrarian(l);
                    String JsonString = elt.librarianToJSON(l);
                    response.getWriter().write(JsonString);
                } else if (su != null) {
                    su.setLat(1.0);
                    su.setLon(1.0);
                    eut.addNewStudent(su);
                    String JsonString = eut.studentToJSON(su);
                    response.getWriter().write(JsonString);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
