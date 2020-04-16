package com.github.hodcko.multy.web;


import com.github.hodcko.multy.service.IServiceCurs;
import com.github.hodcko.multy.service.IServiceStudent;
import com.github.hodcko.multy.service.impl.ServiceCurs;
import com.github.hodcko.multy.service.impl.ServiceStudentManager;
import com.github.hodcko.multy.model.Student;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;



@WebServlet("/student")
public class StudentsRegistrationServlet extends HttpServlet {

    private IServiceStudent iServiceStudent = ServiceStudentManager.getInstance();
    private IServiceCurs iServiceCurs = ServiceCurs.getInstance();

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        String name =  rq.getParameter("name");
        String secondName =  rq.getParameter("secondName");
        String email = rq.getParameter("email");
        int age = Integer.parseInt(rq.getParameter("age"));
        String userType = rq.getParameter("userType");
        String langType = rq.getParameter("langType");

        Student student =  iServiceStudent.saveStudent(name, secondName, email, age, iServiceCurs.getCurs_id(langType));
        HttpSession session = rq.getSession();

        session.setAttribute("student", student);
        session.setAttribute("email", student.getEmail());
        session.setAttribute("userType", userType);

        RequestDispatcher dispatcher = rq.getRequestDispatcher("/SuccessRegistrationNewUser.jsp");
        dispatcher.forward(rq, rs);

    }
}