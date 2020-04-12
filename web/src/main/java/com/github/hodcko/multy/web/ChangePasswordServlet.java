package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.service.ISecurityService;
import com.github.hodcko.multy.service.impl.ServiceAuthUserLogin;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/change")
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ISecurityService iSecurityService = ServiceAuthUserLogin.getInstance();

        AuthUser authUser = (AuthUser) session.getAttribute("authUser");
        String newPassword = req.getParameter("newPassword");
        String newPasswords = req.getParameter("newPasswords");
        String rightPassword = iSecurityService.findPassword(newPassword, newPasswords);
        if(iSecurityService.changePassword(authUser.getLogin(), authUser.getPassword(), rightPassword )){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/LoginFromStartPage.jsp");
            dispatcher.forward(req, resp);
        }else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/InvalidData.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
