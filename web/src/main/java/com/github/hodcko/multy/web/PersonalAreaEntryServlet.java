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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        IServiceAuthUser iServiceAuthUser = ServiceAuthUser.getInstance();
        IServiceAuthUser instance = ServiceAuthUser.getInstance();
        IServiceGetIdByEmail getId = ServiceGetIdByEmail.getInstance();
        IServiceCurs iServiceCurs = ServiceCurs.getInstance();


        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        String userType = (String) session.getAttribute("userType");
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        AuthUser authUser;
        Curs curs;
        List<DTOGroup> dtoGroup;


        if (userType.equalsIgnoreCase("student")) {
//            if (login.equalsIgnoreCase(((Student) session.getAttribute("student")).getName())) {
//                if (password.equalsIgnoreCase(iServiceAuthUser.passwordGenerate(email, userType))) {
                    authUser = instance.saveAuthUser(getId.getId(email, userType), login, password, userType);
                    curs = iServiceCurs.getCurs(((Student) session.getAttribute("student")).getCurs_id());
                    session.setAttribute("authUser", authUser);
                    session.setAttribute("curs", curs);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/personalArea.jsp");
                    dispatcher.forward(req, resp);

//                }
            }
            else if (userType.equalsIgnoreCase("teacher")) {
//                if (login.equalsIgnoreCase(((Teacher) session.getAttribute("teacher")).getName())) {
//                    if (password.equalsIgnoreCase(iServiceAuthUser.passwordGenerate(email, userType))) {
                        authUser = instance.saveAuthUser(getId.getId(email, userType), login, password, userType);
                        curs = iServiceCurs.getCurs(((Teacher) session.getAttribute("teacher")).getCurs_id());
                        dtoGroup = iServiceCurs.getMyStudents(((Teacher) session.getAttribute("teacher")).getCurs_id());
                        session.setAttribute("students", dtoGroup);
                        session.setAttribute("authUser", authUser);
                        session.setAttribute("curs", curs);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("/personalArea.jsp");
                        dispatcher.forward(req, resp);


        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/invalid.jsp");
            dispatcher.forward(req, resp);
        }
    }




}


