package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.service.IServiceAuthUser;
import com.github.hodcko.multy.service.IServiceIsExist;
import com.github.hodcko.multy.service.IServiceValidation;
import com.github.hodcko.multy.service.impl.ServiceAuthUser;
import com.github.hodcko.multy.service.impl.ServiceIsExist;
import com.github.hodcko.multy.service.impl.ServiceValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/validation")
public class ValidationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IServiceIsExist iServiceIsExist = ServiceIsExist.getInstance();
        IServiceValidation iServiceValidation = ServiceValidation.getInstance();
        IServiceAuthUser iServiceAuthUser = ServiceAuthUser.getInstance();

        HttpSession session = req.getSession();
        AuthUser authUser;
        String name = req.getParameter("name");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String userType = req.getParameter("userType");
        resp.getWriter().write(name);
        resp.getWriter().write(secondName);



        if (!iServiceIsExist.isExist(email, userType)) {
            if (userType.equalsIgnoreCase("student")) {
                if (iServiceValidation.validationStudent(name, secondName, email, Integer.parseInt(req.getParameter("age")))) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/student");
                    dispatcher.forward(req, resp);
                }
            }
            else if (userType.equalsIgnoreCase("teacher")) {
                if (iServiceValidation.validationTeacher(name, secondName, email)) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/teacher");
                    dispatcher.forward(req, resp);
                }
            }
        }else {
            authUser = iServiceAuthUser.getAuthUser(name, iServiceAuthUser.passwordGenerate(email, userType));
            session.setAttribute("login", authUser.getLogin());
            session.setAttribute("password", authUser.getPassword());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/AuthUserFalseRegistration.jsp");
            dispatcher.forward(req, resp);
        }
    }
}


