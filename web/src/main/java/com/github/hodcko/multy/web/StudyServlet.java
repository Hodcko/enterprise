package com.github.hodcko.multy.web;



import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceGradebook;
import com.github.hodcko.multy.service.impl.ServiceCursDefault;
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
    private ServiceCurs serviceCurs = ServiceCursDefault.getInstance();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String javaReg = (req.getParameter("javaReg"));
        String phpReg = (req.getParameter("phpReg"));
        String cReg = (req.getParameter("cReg"));

        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");

        session.setAttribute("studentOnCurs", serviceGradebook.isExist(student.getId()));

        if (javaReg != null) {
            serviceCurs.inviteStudentOnCurs(student.getId(), serviceCurs.getCursId(javaReg));
            serviceGradebook.addStudentToGradebook(student.getId(), serviceCurs.getCursId(javaReg));
            session.setAttribute("java", "java");
        }

        if (phpReg != null) {
            serviceCurs.inviteStudentOnCurs(student.getId(), serviceCurs.getCursId(phpReg));
            serviceGradebook.addStudentToGradebook(student.getId(), serviceCurs.getCursId(phpReg));
            session.setAttribute("php", "php");
        }

        if (cReg != null) {
            serviceCurs.inviteStudentOnCurs(student.getId(), serviceCurs.getCursId(cReg));
            serviceGradebook.addStudentToGradebook(student.getId(), serviceCurs.getCursId(cReg));
            session.setAttribute("cPlusPlus", "cPlusPlus");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/StudyPage.jsp");
        dispatcher.forward(req, resp);

    }
}
