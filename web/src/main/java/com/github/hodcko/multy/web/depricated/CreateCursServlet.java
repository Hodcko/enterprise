//package com.github.hodcko.multy.web;
//
//import com.github.hodcko.multy.service.ServiceCurs;
//import com.github.hodcko.multy.service.impl.ServiceCursDefault;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Date;
//import java.time.LocalDate;
//
//@WebServlet("/curs")
//public class CreateCursServlet extends HttpServlet {
//
//    private ServiceCurs serviceCurs = ServiceCursDefault.getInstance();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String cursName = req.getParameter("name");
//        LocalDate startDate = Date.valueOf(req.getParameter("startDate")).toLocalDate();
//        LocalDate endDate = Date.valueOf(req.getParameter("endDate")).toLocalDate();
//
//        serviceCurs.createCurs(cursName, startDate, endDate);
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/personal");
//        dispatcher.forward(req, resp);
//
//    }
//}
