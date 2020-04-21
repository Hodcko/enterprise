package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.*;
import com.github.hodcko.multy.service.*;
import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.impl.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/personalStart")
public class EntryFromStartServlet extends HttpServlet {

    private SecurityService securityService = SecurityServiceDefault.getInstance();
    private ServiceAuthUser serviceAuthUser = ServiceAuthUserDefault.getInstance();
    private ServiceCurs serviceCurs = ServiceCursDefault.getInstance();
    private ServiceStudent serviceStudent = ServiceStudentDefault.getInstance();
    private ServiceTeacher serviceTeacher = ServiceTeacherDefault.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        AuthUser authUser = serviceAuthUser.getAuthUser(login, password);
        String role = authUser.getRole();

        Curs curs;
        List<DTOGroup> dtoGroup;
        Student student;
        Teacher teacher;


        if(login.equalsIgnoreCase(securityService.login(login, password))) {
            if (role.equalsIgnoreCase("student")) {
                student = serviceStudent.getStudent(authUser.getUserId());
                curs = serviceCurs.getCurs(student.getCurs_id());
                session.setAttribute("authUser", authUser);
                session.setAttribute("curs", curs);
                session.setAttribute("student", student);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/PersonalArea.jsp");
                dispatcher.forward(req, resp);
            } else if (role.equalsIgnoreCase("teacher")) {
                teacher = serviceTeacher.getTeacher(authUser.getUserId());
                curs = serviceCurs.getCurs(teacher.getCurs_id());
                dtoGroup = serviceCurs.getMyStudents(teacher.getCurs_id());
                session.setAttribute("students", dtoGroup);
                session.setAttribute("teacher", teacher);
                session.setAttribute("authUser", authUser);
                session.setAttribute("curs", curs);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/PersonalArea.jsp");
                dispatcher.forward(req, resp);
            }
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/InvalidData.jsp");
            dispatcher.forward(req, resp);
        }
    }
}



