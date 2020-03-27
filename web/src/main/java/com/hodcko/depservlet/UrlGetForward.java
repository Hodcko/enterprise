package com.hodcko.depservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UrlGetForward", urlPatterns = {"/urlgetforward"})
public class UrlGetForward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("name").equalsIgnoreCase("hodcko")){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/sayhello");
            dispatcher.forward(req, resp);
        }else{
            PrintWriter writer = resp.getWriter();
            writer.write("Log in please");
        }
    }
}
