package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.ServiceIsExist;
import com.github.hodcko.multy.service.ServiceValidation;
import com.github.hodcko.multy.service.impl.ServiceAuthUserDefault;
import com.github.hodcko.multy.service.impl.ServiceIsExistDefault;
import com.github.hodcko.multy.service.impl.ServiceValidationDefault;

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

    private ServiceIsExist serviceIsExist = ServiceIsExistDefault.getInstance();
    private ServiceValidation serviceValidation = ServiceValidationDefault.getInstance();
    private ServiceAuthUser serviceAuthUser = ServiceAuthUserDefault.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        AuthUser authUser;
        String name = req.getParameter("name");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        UserType userType = UserType.valueOf(req.getParameter("userType").toUpperCase());

        if (!serviceIsExist.isExist(email, userType)) {
            if (userType.equals(UserType.STUDENT)) {
                if (serviceValidation.validationStudent(name, secondName, email, Integer.parseInt(req.getParameter("age")))) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/student");
                    dispatcher.forward(req, resp);
                }
            }
            else if (userType.equals(UserType.TEACHER)) {
                if (serviceValidation.validationTeacher(name, secondName, email)) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/teacher");
                    dispatcher.forward(req, resp);
                }
            }
        }else {
            authUser = serviceAuthUser.getAuthUser(name, serviceAuthUser.passwordGenerate(email, userType));
            session.setAttribute("login", authUser.getLogin());
            session.setAttribute("password", authUser.getPassword());

            RequestDispatcher dispatcher = req.getRequestDispatcher("/AuthUserFalseRegistration.jsp");
            dispatcher.forward(req, resp);
        }
    }
}


