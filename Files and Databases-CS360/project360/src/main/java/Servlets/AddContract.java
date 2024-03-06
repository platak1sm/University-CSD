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
@WebServlet(name = "AddContract", urlPatterns = {"/AddContract"})
public class AddContract extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            boolean m;
            String fname = request.getParameter("FirstName");
            String lname = request.getParameter("LastName");
            String cat = request.getParameter("Category");
            String married = request.getParameter("married");
            if (married == "y") {
                m = true;
            } else {
                m = false;
            }
            String dept = request.getParameter("Department");
            String str = request.getParameter("Street");
            int strn = Integer.parseInt(request.getParameter("StreetNumber"));
            String bank = request.getParameter("Bank");
            String iban = request.getParameter("IBAN");
            String eoc = request.getParameter("EndOfContract");
            double sal = Double.parseDouble(request.getParameter("Salary"));
            try {
                if (Processor.insertNewContractEmployee(fname, lname, cat, m, dept, str, strn, bank, iban, eoc, sal)) {
                    response.setStatus(200);
                } else {
                    response.setStatus(403);
                }
            } catch (ParseException ex) {
                Logger.getLogger(AddContract.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddContract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}