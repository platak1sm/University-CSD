/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

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
import project360_try1.Processor;

/**
 *
 * @author plata
 */
@WebServlet(name = "EditEmp", urlPatterns = {"/EditEmp"})
public class EditEmp extends HttpServlet {

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
            int eid = Integer.parseInt(request.getParameter("eid"));
            int leid = Processor.findEidOfLastInsertedEmployee();
            if (eid > leid) {
                response.setStatus(403);
            } else {
                String fname = request.getParameter("FirstName");
                String lname = request.getParameter("LastName");
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
                PrintWriter out = response.getWriter();

                if (fname != "") {
                    Processor.setFirstName(eid, fname);
                }
                if (lname != "") {
                    Processor.setLastName(eid, lname);
                }
                Processor.setMarried(eid, m);
                if (dept != "") {
                    Processor.setDepartment(eid, dept);
                }
                if (str != "") {
                    Processor.setStreet(eid, str);
                }
                if (strn != 0) {
                    Processor.setStreetNumber(eid, strn);
                }
                if (bank != "") {
                    Processor.setBank(eid, bank);
                }
                if (iban != "") {
                    Processor.setIBAN(eid, iban);
                }
                response.setStatus(200);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditEmp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
