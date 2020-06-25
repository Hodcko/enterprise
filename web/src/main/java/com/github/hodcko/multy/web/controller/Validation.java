package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.ServiceIsExist;
import com.github.hodcko.multy.service.ServiceValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping
public class Validation {
    private static final Logger log = LoggerFactory.getLogger(Validation.class);

    AuthUser authUser;

    private final ServiceIsExist serviceIsExist;
    private final ServiceValidation serviceValidation;
    private final ServiceAuthUser serviceAuthUser;

    public Validation(ServiceIsExist serviceIsExist, ServiceValidation serviceValidation, ServiceAuthUser serviceAuthUser) {
        this.serviceIsExist = serviceIsExist;
        this.serviceValidation = serviceValidation;
        this.serviceAuthUser = serviceAuthUser;
    }


    @GetMapping("/")
    public String doGet() {
        return "StartPage";
    }




    @PostMapping("/validation")
    public String validation(HttpServletRequest req, HttpSession session) {
        String name = req.getParameter("name");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        UserType userType = UserType.valueOf(req.getParameter("userType").toUpperCase());

        if (!serviceIsExist.isExist(email, userType)) {
            if (userType.equals(UserType.STUDENT)) {
                if (serviceValidation.validationStudent(name, secondName, email, Integer.parseInt(req.getParameter("age")))) {
                    return "forward:/student";
                }
            }
            else if (userType.equals(UserType.TEACHER)) {
                if (serviceValidation.validationTeacher(name, secondName, email)) {
                    return "forward:/teacher";
                }
            }
        }else {
            authUser = serviceAuthUser.getAuthUser(name, serviceAuthUser.passwordGenerate(email, userType));
            session.setAttribute("login", authUser.getLogin());
            session.setAttribute("password", authUser.getPassword());
            log.info("created authUser with login {} and password {}", authUser.getLogin(), authUser.getPassword());
            return "AuthUserFalseRegistration";
        }
        return "InvalidData";
    }
}
