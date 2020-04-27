package com.github.hodcko.multy.web;


import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.service.ServiceGradebook;
import com.github.hodcko.multy.service.impl.ServiceGradebookDefault;

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
    private ServiceGradebook serviceGradebook = ServiceGradebookDefault.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");

        serviceGradebook.addStudentToGradebook(student.getId());

        session.setAttribute("studentOnCurs", serviceGradebook.isExist(student.getId()));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/StudyPage.jsp");
        dispatcher.forward(req, resp);

    }
}
