package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceAuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class Login {
    private static final Logger log = LoggerFactory.getLogger(Login.class);
    private final ServiceAuthUser serviceAuthUser;
    private AuthUser authUser;


    public Login(ServiceAuthUser serviceAuthUser) {
        this.serviceAuthUser = serviceAuthUser;
    }

    @GetMapping("/loginAfterRegistration")
    public String loginAfterRegistration() {
        return "LoginAfterRegistration";
    }

    @PostMapping("/loginFromStartPage")
    public String loginFromStartPage(HttpServletRequest rq) {
        String login;
        String password;
        login = rq.getParameter("login");
        password = rq.getParameter("password");
        if(rq.getParameter("login") == null){
            login = (String) rq.getAttribute("login");
        }
        if(rq.getParameter("password") == null){
            password = (String) rq.getAttribute("password");
        }
        authUser = serviceAuthUser.getAuthUser(login, password);
        if (authUser == null) {
            rq.setAttribute("error", "login or password invalid");
            return "InvalidData";
        }
        log.info("user {} logged", authUser.getLogin());
        Authentication auth = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "forward:/personalStart";
    }

    @PostMapping("/loginAfterRegistration")
    public String loginAfterRegistration(HttpServletRequest rq, HttpSession session) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        UserType userType = UserType.valueOf(session.getAttribute("userType").toString().toUpperCase());
        if(userType.equals(UserType.STUDENT)){
            Student student = (Student) session.getAttribute("student");
            if(student.getName().equalsIgnoreCase(login)&&
                    (password.equalsIgnoreCase(serviceAuthUser.passwordGenerate(student.getEmail(), UserType.STUDENT)))){
                authUser = serviceAuthUser.saveAuthUser(student.getId(), student.getName(),
                        student.getSecondName()+student.getId(), UserType.STUDENT);
                log.info("user {} logged", authUser.getLogin());
                Authentication auth = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                return "forward:/personal";
            }else{
                rq.setAttribute("error", "login or password invalid");
                return "InvalidData";
            }
        }else if(userType.equals(UserType.TEACHER)) {
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            if (teacher.getName().equalsIgnoreCase(login) &&
                    (password.equalsIgnoreCase(serviceAuthUser.passwordGenerate(teacher.getEmail(), UserType.TEACHER)))) {
                authUser = serviceAuthUser.saveAuthUser(teacher.getId(), teacher.getName(),
                        teacher.getSecondName()+teacher.getId(), UserType.TEACHER);
                log.info("user {} logged", authUser.getLogin());
                Authentication auth = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                return "forward:/personal";
            } else {
                rq.setAttribute("error", "login or password invalid");
                return "InvalidData";
            }
        }
        else{
            rq.setAttribute("error", "login or password invalid");
            return "InvalidData";
        }
    }

    private List<GrantedAuthority> getAuthorities() {
        if(authUser.getRole().equals(UserType.STUDENT)){
            return Arrays.asList((GrantedAuthority) () -> "ROLE_STUDENT");
        }
        else{
            return Arrays.asList((GrantedAuthority) () -> "ROLE_TEACHER");
        }
    }
}
