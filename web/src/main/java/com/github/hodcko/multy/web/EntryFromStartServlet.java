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
    private ServiceGradebook serviceGradebook = ServiceGradebookDefault.getInstance();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        AuthUser authUser = serviceAuthUser.getAuthUser(login, password);
        UserType role = authUser.getRole();

        Curs curs;
        List<GroupDTO> groupDTO;
        Student student;
        Teacher teacher;


        if(login.equalsIgnoreCase(securityService.login(login, password))) {
            if (role.equals(UserType.STUDENT)) {
                student = serviceStudent.getStudent(authUser.getUserId());
                curs = serviceCurs.getCurs(student.getCursId());

                session.setAttribute("authUser", authUser);
                session.setAttribute("curs", curs);
                session.setAttribute("student", student);

                if(serviceGradebook.isExist(student.getId())){
                    List<Student> classmates = serviceCurs.getClassmates(student.getCursId());
                    session.setAttribute("studentOnCurs", serviceGradebook.isExist(student.getId()));
                    session.setAttribute("classmates", classmates);
                }
                RequestDispatcher dispatcher = req.getRequestDispatcher("/PersonalArea.jsp");
                dispatcher.forward(req, resp);
            } else if (role.equals(UserType.TEACHER)) {

                int page = 1;
                teacher = serviceTeacher.getTeacher(authUser.getUserId());
                curs = serviceCurs.getCurs(teacher.getCursId());
                groupDTO = serviceCurs.getMyStudents(teacher.getCursId(), page);
                int cursId = (teacher.getCursId());
                int noOfRecords = serviceCurs.countOfStudents(cursId);
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 1);

                req.setAttribute("noOfPages", noOfPages);
                req.setAttribute("currentPage", page);
                session.setAttribute("students", groupDTO);
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



