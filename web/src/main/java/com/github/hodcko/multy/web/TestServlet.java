package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.service.IServiceGradebook;
import com.github.hodcko.multy.service.impl.ServiceGradebook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IServiceGradebook iServiceGradebook = ServiceGradebook.getInstance();
        HttpSession session = req.getSession();
        Student student = (Student)session.getAttribute("student");

        String firstQ = req.getParameter("first");
        String secondQ = req.getParameter("second");
        String thirdQ = req.getParameter("third");
        String fourthQ = req.getParameter("fourth");
        String fifthQ = req.getParameter("fifth");

        int resultOfTest = iServiceGradebook.checkTest(student.getId(), firstQ, secondQ, thirdQ, fourthQ, fifthQ);

        req.setAttribute("result", resultOfTest);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
        dispatcher.forward(req, resp);


    }
}
