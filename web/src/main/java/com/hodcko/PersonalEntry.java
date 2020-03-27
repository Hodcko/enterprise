package com.hodcko;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/personal")
public class PersonalEntry extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Service service = StudentService.getInstance();

        if(req.getParameter("login").equalsIgnoreCase("admin")&&
        req.getParameter("password").equalsIgnoreCase("admin")){
            Dao dao = StudentManager.getInstance();
            List<Student> students = service.getStudentsList();
            HttpSession session = req.getSession();
            session.setAttribute("students", students);
            session.setAttribute("manager", dao);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/personalArea.jsp");
            dispatcher.forward(req, resp);
        }else {
            for (Student o : service.getStudentsList()) {
                if (o.getName().equalsIgnoreCase(req.getParameter("login")) &&
                        (String.valueOf(o.getId())).equals(req.getParameter("password"))) {
                    HttpSession session = req.getSession();
                    session.setAttribute("islogin", o);
                   // req.setAttribute("student", o);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/personalArea.jsp");
                    dispatcher.forward(req, resp);
                }
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/invalid.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
