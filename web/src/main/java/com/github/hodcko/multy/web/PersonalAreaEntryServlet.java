package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.*;
import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceGetIdByEmail;
import com.github.hodcko.multy.service.ServiceGradebook;
import com.github.hodcko.multy.service.impl.ServiceAuthUserDefault;
import com.github.hodcko.multy.service.impl.ServiceCursDefault;
import com.github.hodcko.multy.service.impl.ServiceGetIdByEmailDefault;
import com.github.hodcko.multy.service.impl.ServiceGradebookDefault;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/personal")
public class PersonalAreaEntryServlet extends HttpServlet {
    private ServiceAuthUser instance = ServiceAuthUserDefault.getInstance();
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
            int cursId = ((Student) session.getAttribute("student")).getCursId();
            authUser = instance.saveAuthUser(getId.getId(email, userType), login, password, userType);
            curs = serviceCurs.getCurs(cursId);
            List<Student> classmates = serviceCurs.getClassmates(cursId);

            session.setAttribute("studentOnCurs", serviceGradebook.isExist(((Student) session.getAttribute("student")).getId()));
            session.setAttribute("authUser", authUser);
            session.setAttribute("curs", curs);
            session.setAttribute("classmates", classmates);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/PersonalArea.jsp");
            dispatcher.forward(req, resp);
        } else if (userType.equals(UserType.TEACHER)) {
            int page = 1;
            int cursId = ((Teacher) session.getAttribute("teacher")).getCursId();
            int noOfRecords = serviceCurs.countOfStudents(cursId);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 1);


            authUser = instance.saveAuthUser(getId.getId(email, userType), login, password, userType);
            curs = serviceCurs.getCurs(cursId);
            groupDTO = serviceCurs.getMyStudents(cursId, page);

            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            session.setAttribute("students", groupDTO);
            session.setAttribute("authUser", authUser);
            session.setAttribute("curs", curs);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/PersonalArea.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/InvalidData.jsp");
            dispatcher.forward(req, resp);
        }
    }
}


