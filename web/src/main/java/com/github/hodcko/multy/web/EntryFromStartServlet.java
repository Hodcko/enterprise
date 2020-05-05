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
        if(login == null){
            login = (String)req.getAttribute("login");
        }
        String password = req.getParameter("password");
        if(password == null){
            password = (String)req.getAttribute("password");
        }

        String langTypeJava = "java";
        String langTypePHP = "php";
        String langTypeC = "c++";

        AuthUser authUser = serviceAuthUser.getAuthUser(login, password);
        UserType role = authUser.getRole();

        Curs curs;
        List<GroupDTO> groupDTO;
        Student student;
        Teacher teacher;


        if(login.equalsIgnoreCase(securityService.login(login, password))) {
            if (role.equals(UserType.STUDENT)) {
                student = serviceStudent.getStudent(authUser.getUserId());

                boolean studentOnCurs = false;
                if(serviceGradebook.isExist(student.getId())){
                   studentOnCurs = true;
                }
                session.setAttribute("studentOnCurs", studentOnCurs);

                session.setAttribute("authUser", authUser);
                session.setAttribute("student", student);

                Curs javaCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypeJava));
                session.setAttribute("javaCurs", javaCurs);

                Curs phpCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypePHP));
                session.setAttribute("phpCurs", phpCurs);

                Curs cCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypeC));
                session.setAttribute("cCurs", cCurs);

                if(serviceCurs.getClassmates(serviceCurs.getCursId(langTypeJava)).contains(student)){
                    session.setAttribute("classmatesJava", serviceCurs.getClassmates(serviceCurs.getCursId(langTypeJava)));
                    session.setAttribute("java", "java");
                }
                if(serviceCurs.getClassmates(serviceCurs.getCursId(langTypePHP)).contains(student)){
                    session.setAttribute("classmatesPHP", serviceCurs.getClassmates(serviceCurs.getCursId(langTypePHP)));
                    session.setAttribute("php", "php");
                }
                if(serviceCurs.getClassmates(serviceCurs.getCursId(langTypeC)).contains(student)){
                    session.setAttribute("classmatesC", serviceCurs.getClassmates(serviceCurs.getCursId(langTypeC)));
                    session.setAttribute("cPlusPlus", "cPlusPlus");
                }

                RequestDispatcher dispatcher = req.getRequestDispatcher("/StudentPersonalArea.jsp");
                dispatcher.forward(req, resp);

            } else if (role.equals(UserType.TEACHER)) {

                int page = 1;
                teacher = serviceTeacher.getTeacher(authUser.getUserId());
                curs = serviceCurs.getCurs(teacher.getCursId());
                groupDTO = serviceCurs.getMyStudents(teacher.getCursId(), page);
                int cursId = teacher.getCursId();
                int noOfRecords = serviceCurs.countOfStudents(cursId);
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 3);
                List<Teacher> teachers = serviceCurs.getColleagues(cursId);

                req.setAttribute("noOfPages", noOfPages);
                req.setAttribute("currentPage", page);
                session.setAttribute("students", groupDTO);
                session.setAttribute("teachers", teachers);
                session.setAttribute("teacher", teacher);
                session.setAttribute("authUser", authUser);
                session.setAttribute("curs", curs);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/TeacherPersonalArea.jsp");
                dispatcher.forward(req, resp);
            }
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/InvalidData.jsp");
            dispatcher.forward(req, resp);
        }
    }
}



