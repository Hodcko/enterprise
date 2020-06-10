package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceStudent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping
public class StudentRegistration {
    private static final Logger log = LoggerFactory.getLogger(StudentRegistration.class);

    private final ServiceStudent serviceStudent;

    public StudentRegistration(ServiceStudent serviceStudent) {
        this.serviceStudent = serviceStudent;
    }

    @PostMapping("/student")
    public String doPost(HttpServletRequest req, HttpSession session) {
        String name =  req.getParameter("name");
        String secondName =  req.getParameter("secondName");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        UserType userType = UserType.valueOf(req.getParameter("userType").toUpperCase()) ;

        Student student =  serviceStudent.saveStudent(name, secondName, email, age);

        session.setAttribute("student", student);
        session.setAttribute("email", student.getEmail());
        session.setAttribute("userType", userType);
        session.setAttribute("langTypeJava", req.getParameter("langTypeJava"));
        session.setAttribute("langTypePHP", req.getParameter("langTypePHP"));
        session.setAttribute("langTypeC", req.getParameter("langTypeC"));
        log.info("created student with email {} ", student.getEmail());

        return "forward:/SuccessRegistrationNewUser.jsp";
    }




}
