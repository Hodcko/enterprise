package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceTeacher;
import com.github.hodcko.multy.service.impl.ServiceCursDefault;
import com.github.hodcko.multy.service.impl.ServiceTeacherDefault;
import com.github.hodcko.multy.model.Teacher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/teacher")
public class TeacherRegistrationServlet extends HttpServlet {

    private ServiceTeacher serviceTeacher = ServiceTeacherDefault.getInstance();
    private ServiceCurs serviceCurs = ServiceCursDefault.getInstance();

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        String name =  rq.getParameter("name");
        String secondName =  rq.getParameter("secondName");
        String email = rq.getParameter("email");
        UserType userType = UserType.valueOf(rq.getParameter("userType").toUpperCase()) ;
        String langType = rq.getParameter("langType");

        Teacher teacher =  serviceTeacher.saveTeacher(name, secondName, email, serviceCurs.getCurs_id(langType));
        HttpSession session = rq.getSession();

        session.setAttribute("teacher", teacher);
        session.setAttribute("email", teacher.getEmail());
        session.setAttribute("userType", userType);

        RequestDispatcher dispatcher = rq.getRequestDispatcher("/SuccessRegistrationNewUser.jsp");
        dispatcher.forward(rq, rs);

    }
}