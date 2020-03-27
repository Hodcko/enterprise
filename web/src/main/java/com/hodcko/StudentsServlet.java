package com.hodcko;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;



@WebServlet("/student")
public class StudentsServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        Service service = StudentService.getInstance();

        String name =  rq.getParameter("name");
        String secondName =  rq.getParameter("secondName");

        Student student = new Student(name, secondName);
        service.saveStudent(student);
        HttpSession session = rq.getSession();
        session.setAttribute("student", student);

       // rq.setAttribute("student", student);

        RequestDispatcher dispatcher = rq.getRequestDispatcher("/succes.jsp");
        dispatcher.forward(rq, rs);

    }
}