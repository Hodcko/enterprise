package com.hodcko;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/spec")
public class Specialization extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("islogin");
        String spec = req.getParameter("lang");
        student.setSpec(spec);
        req.setAttribute("student", student);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/personalArea.jsp");
        dispatcher.forward(req, resp);
    }
}
