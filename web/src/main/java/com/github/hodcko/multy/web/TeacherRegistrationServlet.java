package com.github.hodcko.multy.web;

import com.github.hodcko.multy.service.IServiceCurs;
import com.github.hodcko.multy.service.IServiceTeacher;
import com.github.hodcko.multy.service.impl.ServiceCurs;
import com.github.hodcko.multy.service.impl.ServiceTeacherManager;
import com.github.hodcko.multy.model.Teacher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/teacher")
public class TeacherRegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        IServiceTeacher iServiceTeacher = ServiceTeacherManager.getInstance();
        IServiceCurs iServiceCurs = ServiceCurs.getInstance();


        String name =  rq.getParameter("name");
        String secondName =  rq.getParameter("secondName");
        String email = rq.getParameter("email");
        String userType = rq.getParameter("userType");
        String langType = rq.getParameter("langType");

        Teacher teacher =  iServiceTeacher.saveTeacher(name, secondName, email, iServiceCurs.getCurs_id(langType));
        HttpSession session = rq.getSession();

        session.setAttribute("teacher", teacher);
        session.setAttribute("email", teacher.getEmail());
        session.setAttribute("userType", userType);

        RequestDispatcher dispatcher = rq.getRequestDispatcher("/SuccessRegistrationNewUser.jsp");
        dispatcher.forward(rq, rs);

    }
}