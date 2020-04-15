package com.github.hodcko.multy.web.filter;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/personal")
public class PersonalAreaFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        String userType = (String) session.getAttribute("userType");
        if(userType.equalsIgnoreCase("student")){
            Student student = (Student) session.getAttribute("student");
            if(student.getName().equalsIgnoreCase(login)&&
                    (password.equalsIgnoreCase(student.getSecondName()+student.getId()))){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/InvalidData.jsp");
                dispatcher.forward(servletRequest, servletResponse);
            }
        }else if(userType.equalsIgnoreCase("teacher")) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            if (teacher.getName().equalsIgnoreCase(login) &&
                    (password.equalsIgnoreCase(teacher.getSecondName() + teacher.getId()))) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/InvalidData.jsp");
                dispatcher.forward(servletRequest, servletResponse);
            }
        }
        else{
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/InvalidData.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}