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
@WebServlet(name = "EditBS", urlPatterns = {"/EditBS"})
public class EditBS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double bas, bts;
        if (request.getParameter("BasicAdministrativeSalary") != "") {
            try {
                bas = Double.parseDouble(request.getParameter("BasicAdministrativeSalary"));
                Processor.setBasicAdministrativeSalary(bas);
            } catch (SQLException ex) {
                Logger.getLogger(EditBS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(EditBS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (request.getParameter("BasicTeachingSalary") != "") {
            try {
                bts = Double.parseDouble(request.getParameter("BasicTeachingSalary"));
                Processor.setBasicTeachingSalary(bts);
            } catch (SQLException ex) {
                Logger.getLogger(EditBS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(EditBS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.setStatus(200);
        if (request.getParameter("BasicTeachingSalary") == "" && request.getParameter("BasicAdministrativeSalary") == "") {
            response.setStatus(403);
        }

    }

}
