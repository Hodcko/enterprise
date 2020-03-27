package com.hodcko.depservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetCookies", urlPatterns = {"/getcookies"})
public class GetCookies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        PrintWriter pw = resp.getWriter();

        pw.println("<html>");

        for (Cookie cookie : cookies) {
            pw.println("<h1>" + cookie.getName() + ":" + cookie.getValue() + "</h1>");
        }
        pw.println("</html>");
    }
}
