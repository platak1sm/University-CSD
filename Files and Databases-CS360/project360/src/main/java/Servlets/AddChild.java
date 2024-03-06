/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project360_try1.Processor;

/**
 *
 * @author plata
 */
@WebServlet(name = "AddChild", urlPatterns = {"/AddChild"})
public class AddChild extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int eid = Integer.parseInt(request.getParameter("eid"));
            String fname = request.getParameter("FirstName");
            String lname = request.getParameter("LastName");
            String bday = request.getParameter("Birthday");
            if (eid <= Processor.findEidOfLastInsertedEmployee()) {
                Processor.addChild(eid, fname, lname, bday);
                response.setStatus(200);
            } else {
                response.setStatus(403);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddChild.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddChild.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
