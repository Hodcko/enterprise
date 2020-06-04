//package com.github.hodcko.multy.web;
//
//import com.github.hodcko.multy.model.AuthUser;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet("/clean")
//public class CleanAfterFullRegistration extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        HttpSession session = req.getSession();
//        AuthUser authUser = (AuthUser)session.getAttribute("authUser");
//        session.invalidate();
//        req.setAttribute("login", authUser.getLogin());
//        req.setAttribute("password", authUser.getPassword());
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/personalStart");
//        dispatcher.forward(req, resp);
//    }
//}
