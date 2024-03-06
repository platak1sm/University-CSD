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
@WebServlet(name = "AvgSAI", urlPatterns = {"/AvgSAI"})
public class AvgSAI extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String s = request.getParameter("start");
            String e = request.getParameter("end");
            PrintWriter out = response.getWriter();
            String str = Processor.getAvgSalaryAndAllowanceIncrease(s, e);
            out.println("<!DOCTYPE html>");
            out.println(str);
            response.setStatus(200);
        } catch (SQLException ex) {
            Logger.getLogger(MaxMinAvg.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
