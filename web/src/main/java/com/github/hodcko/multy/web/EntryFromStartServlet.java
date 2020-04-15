package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.*;
import com.github.hodcko.multy.service.*;
import com.github.hodcko.multy.service.impl.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/personalStart")
public class EntryFromStartServlet extends HttpServlet {
    ISecurityService iSecurityService = ServiceAuthUserLogin.getInstance();
    IServiceAuthUser iServiceAuthUser = ServiceAuthUser.getInstance();
    IServiceCurs iServiceCurs = ServiceCurs.getInstance();
    IServiceStudent iServiceStudent = ServiceStudentManager.getInstance();
    IServiceTeacher iServiceTeacher = ServiceTeacherManager.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        AuthUser authUser = iServiceAuthUser.getAuthUser(login, password);
        String role = authUser.getRole();

        Curs curs;
        List<DTOGroup> dtoGroup;
        Student student;
        Teacher teacher;


        if(login.equalsIgnoreCase(iSecurityService.login(login, password))) {
            if (role.equalsIgnoreCase("student")) {
                student = iServiceStudent.getStudent(authUser.getUserId());
                curs = iServiceCurs.getCurs(student.getCurs_id());
                session.setAttribute("authUser", authUser);
                session.setAttribute("curs", curs);
                session.setAttribute("student", student);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/PersonalArea.jsp");
                dispatcher.forward(req, resp);
            } else if (role.equalsIgnoreCase("teacher")) {
                teacher = iServiceTeacher.getTeacher(authUser.getUserId());
                curs = iServiceCurs.getCurs(teacher.getCurs_id());
                dtoGroup = iServiceCurs.getMyStudents(teacher.getCurs_id());
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



