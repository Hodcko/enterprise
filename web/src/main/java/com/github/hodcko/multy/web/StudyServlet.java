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


@WebServlet("/study")
public class StudyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");

        IServiceGradebook iServiceGradebook = ServiceGradebook.getInstance();
        iServiceGradebook.addStudentToGradebook(student.getId());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/study.jsp");
        dispatcher.forward(req, resp);

    }
}
