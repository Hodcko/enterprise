package com.hodcko;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/study")
public class Study extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService service = new StudentService();

        List<Student> students = service.getStudentsList();
        HttpSession session = req.getSession();
        if(students.contains((Student) session.getAttribute("islogin"))){
            req.setAttribute("lang", service.getSpec((Student) session.getAttribute("islogin")));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/study.jsp");
            dispatcher.forward(req, resp);
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/studyreg.jsp");
            dispatcher.forward(req, resp);
            }
    }
}
