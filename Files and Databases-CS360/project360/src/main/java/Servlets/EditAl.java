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
@WebServlet(name = "EditAl", urlPatterns = {"/EditAl"})
public class EditAl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double ra;
        double la;
        double fap;
        try {
            if (request.getParameter("ResearchAllowance") != "") {
                ra = Double.parseDouble(request.getParameter("ResearchAllowance"));
                Processor.setResearchAllowance(ra);
            }
            if (request.getParameter("LibraryAllowance") != "") {
                la = Double.parseDouble(request.getParameter("LibraryAllowance"));
                Processor.setLibraryAllowance(la);
            }
            if (request.getParameter("FamilyAllowancePercentage") != "") {
                fap = Double.parseDouble(request.getParameter("FamilyAllowancePercentage"));
                Processor.setFamilyAllowancePercentage(fap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditAl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EditAl.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setStatus(200);

        if (request.getParameter("ResearchAllowance") == "" && request.getParameter("LibraryAllowance") == "" && request.getParameter("FamilyAllowancePercentage") == "") {
            response.setStatus(403);
        }
    }

}
