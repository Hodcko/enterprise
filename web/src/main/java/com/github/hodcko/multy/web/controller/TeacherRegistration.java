package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceTeacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping
public class TeacherRegistration {
    private static final Logger log = LoggerFactory.getLogger(TeacherRegistration.class);

    private final ServiceTeacher serviceTeacher;
    private final ServiceCurs serviceCurs;


    public TeacherRegistration(ServiceTeacher serviceTeacher, ServiceCurs serviceCurs) {
        this.serviceTeacher = serviceTeacher;
        this.serviceCurs = serviceCurs;
    }

    @PostMapping("/teacher")
    public String doPost(HttpServletRequest req) {
        String name =  req.getParameter("name");
        String secondName =  req.getParameter("secondName");
        String email = req.getParameter("email");
        UserType userType = UserType.valueOf(req.getParameter("userType").toUpperCase()) ;
        String langType = req.getParameter("langType");

        Teacher teacher =  serviceTeacher.saveTeacher(name, secondName, email, serviceCurs.getCursId(langType));
        HttpSession session = req.getSession();

        session.setAttribute("teacher", teacher);
        session.setAttribute("email", teacher.getEmail());
        session.setAttribute("userType", userType);
        log.info("created teacher with email {} ", teacher.getEmail());

        return "forward:/SuccessRegistrationNewUser.jsp";
    }




}
