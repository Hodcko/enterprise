package com.hodcko;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/locale")
public class Locale extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locale = req.getParameter("locale");
        if(locale == null){
            req.getSession().setAttribute("locale", "en_US");
        }else {
            req.getSession().setAttribute("locale", locale);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/registration.jsp");
        dispatcher.forward(req, resp);

    }
}
