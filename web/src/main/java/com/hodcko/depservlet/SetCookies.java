package com.hodcko.depservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SetCookies", urlPatterns = {"/setcookies"})
public class SetCookies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("id", "123");
        Cookie cookie1 = new Cookie("name", "John");

        cookie.setMaxAge(24 * 60 * 60);
        cookie1.setMaxAge(24 * 60 * 60);

        resp.addCookie(cookie);
        resp.addCookie(cookie1);
    }
}
