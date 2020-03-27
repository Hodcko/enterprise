package com.hodcko.depservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Session", urlPatterns = "/session")
public class Session extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer count = (Integer) session.getAttribute("count");

        if(count == null){
            session.setAttribute("count", 1);
            count = 1;
        }else{
            session.setAttribute("count", count + 1);
        }

        PrintWriter pw = resp.getWriter();

        pw.write("Your count is " + count);

    }
}
