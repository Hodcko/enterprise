package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.service.IServiceAuthUser;
import com.github.hodcko.multy.service.impl.ServiceAuthUser;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/escape")
public class EscapeCursServlet extends HttpServlet {
    private IServiceAuthUser iServiceAuthUser = ServiceAuthUser.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(req.getParameter("escape").equalsIgnoreCase("escape")){
            if(session.getAttribute("student") != null){
                Student student = (Student) session.getAttribute("student");
                iServiceAuthUser.deleteAuthUser(student.getId(), "student");
            }
            if (session.getAttribute("teacher") != null){
                Teacher teacher = (Teacher) session.getAttribute("teacher");
                iServiceAuthUser.deleteAuthUser(teacher.getId(), "teacher");
            }
            req.getSession().invalidate();
            RequestDispatcher dispatcher = req.getRequestDispatcher("/start");
            dispatcher.forward(req, resp);
        }
    }
}
