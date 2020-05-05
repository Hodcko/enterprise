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


@WebServlet("/personal")
public class PersonalAreaEntryServlet extends HttpServlet {
    private ServiceAuthUser saveAuthUser = ServiceAuthUserDefault.getInstance();
    private ServiceGetIdByEmail getId = ServiceGetIdByEmailDefault.getInstance();
    private ServiceCurs serviceCurs = ServiceCursDefault.getInstance();
    private ServiceGradebook serviceGradebook = ServiceGradebookDefault.getInstance();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        UserType userType = UserType.valueOf(session.getAttribute("userType").toString().toUpperCase());
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        AuthUser authUser;
        Curs curs;
        List<GroupDTO> groupDTO;

        if (userType.equals(UserType.STUDENT)) {
            String langTypeJava = (String) session.getAttribute("langTypeJava");
            String langTypePHP = (String) session.getAttribute("langTypePHP");
            String langTypeC = (String) session.getAttribute("langTypeC");

            authUser = saveAuthUser.saveAuthUser(getId.getId(email, userType), login, password, userType);

            if(langTypeJava != null){
                Curs javaCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypeJava));
                session.setAttribute("javaCurs", javaCurs);
                session.setAttribute("classmatesJava", serviceCurs.getClassmates(serviceCurs.getCursId(langTypeJava)));
            }
            if(langTypePHP != null){
                Curs phpCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypePHP));
                session.setAttribute("phpCurs", phpCurs);
                session.setAttribute("classmatesPHP", serviceCurs.getClassmates(serviceCurs.getCursId(langTypePHP)));
            }
            if(langTypeC != null){
                Curs cCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypeC));
                session.setAttribute("cCurs", cCurs);
                session.setAttribute("classmatesC", serviceCurs.getClassmates(serviceCurs.getCursId(langTypeC)));
            }
            session.setAttribute("studentOnCurs", serviceGradebook.isExist(((Student) session.getAttribute("student")).getId()));
            session.setAttribute("authUser", authUser);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/StudentPersonalArea.jsp");
            dispatcher.forward(req, resp);

        } else if (userType.equals(UserType.TEACHER)) {
            Teacher teacher = ((Teacher) session.getAttribute("teacher"));
            int cursId = teacher.getCursId();
            int page = 1;
            int numberOfRecordsOnPage = 3;
            int noOfRecords = serviceCurs.countOfStudents(cursId);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / numberOfRecordsOnPage);

            authUser = saveAuthUser.saveAuthUser(teacher.getId(), teacher.getName(),
                    saveAuthUser.passwordGenerate(teacher.getEmail(), userType), userType);
            curs = serviceCurs.getCurs(cursId);
            groupDTO = serviceCurs.getMyStudents(cursId, page);
            List<Teacher> teachers = serviceCurs.getColleagues(cursId);

            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            session.setAttribute("students", groupDTO);
            session.setAttribute("teachers", teachers);
            session.setAttribute("authUser", authUser);
            session.setAttribute("curs", curs);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/TeacherPersonalArea.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/InvalidData.jsp");
            dispatcher.forward(req, resp);
        }
    }
}


