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


@Controller
@RequestMapping
public class StudentRegistration {
    private static final Logger log = LoggerFactory.getLogger(StudentRegistration.class);

    private final ServiceStudent serviceStudent;

    public StudentRegistration(ServiceStudent serviceStudent) {
        this.serviceStudent = serviceStudent;
    }

    @PostMapping("/student")
    public String doPost(HttpServletRequest req) {
        String name =  req.getParameter("name");
        String secondName =  req.getParameter("secondName");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        UserType userType = UserType.valueOf(req.getParameter("userType").toUpperCase()) ;
//        String langTypeJava = req.getParameter("langTypeJava");

        Student student =  serviceStudent.saveStudent(name, secondName, email, age);

        req.getSession().setAttribute("student", student);
        req.getSession().setAttribute("email", student.getEmail());
        req.getSession().setAttribute("userType", userType);
        req.getSession().setAttribute("langTypeJava", req.getParameter("langTypeJava"));
        req.getSession().setAttribute("langTypePHP", req.getParameter("langTypePHP"));
        req.getSession().setAttribute("langTypeC", req.getParameter("langTypeC"));
        log.info("created student with email {} ", student.getEmail());

        return "forward:/SuccessRegistrationNewUser.jsp";
    }




}
