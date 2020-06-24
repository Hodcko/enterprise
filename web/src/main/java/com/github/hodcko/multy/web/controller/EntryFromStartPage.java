package com.github.hodcko.multy.web.controller;


import com.github.hodcko.multy.dao.entity.GroupDTO;
import com.github.hodcko.multy.model.*;
import com.github.hodcko.multy.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class EntryFromStartPage {
    private static final Logger log = LoggerFactory.getLogger(EntryFromStartPage.class);


    private final SecurityService securityService;
    private final ServiceAuthUser serviceAuthUser;
    private final ServiceCurs serviceCurs;
    private final ServiceStudent serviceStudent;
    private final ServiceTeacher serviceTeacher;
    private final ServiceGradebook serviceGradebook;

    public EntryFromStartPage(SecurityService securityService, ServiceAuthUser serviceAuthUser, ServiceCurs serviceCurs,
                              ServiceStudent serviceStudent, ServiceTeacher serviceTeacher, ServiceGradebook serviceGradebook) {
        this.securityService = securityService;
        this.serviceAuthUser = serviceAuthUser;
        this.serviceCurs = serviceCurs;
        this.serviceStudent = serviceStudent;
        this.serviceTeacher = serviceTeacher;
        this.serviceGradebook = serviceGradebook;
    }

    @GetMapping("/enterFromStartPage")
    public String startPageLogin() {
        AuthUser authUser;
        try {
            authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (ClassCastException e){
            return "LoginFromStartPage";
        }
        if(authUser != null){
            if(authUser.getRole().equals(UserType.STUDENT)){
                return "StudentPersonalArea";
            }else {
                return "TeacherPersonalArea";
            }
        }
        return "LoginFromStartPage";
    }

    @PostMapping("/personalStart")
    public String entryFromStartPage(HttpServletRequest req, HttpSession session) {

        String login = req.getParameter("login");
        if(login == null){
            login = (String)req.getAttribute("login");
        }
        String password = req.getParameter("password");
        if(password == null){
            password = (String)req.getAttribute("password");
        }

        String langTypeJava = "java";
        String langTypePHP = "php";
        String langTypeC = "c++";

        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      //  AuthUser authUser = serviceAuthUser.getAuthUser(login, password);
        UserType role = authUser.getRole();

        Curs curs;
        List<GroupDTO> groupDTO;
        Student student;
        Teacher teacher;

        if(login.equalsIgnoreCase(securityService.login(login, password))) {
            if (role.equals(UserType.STUDENT)) {
                student = serviceStudent.getStudent(authUser.getUserId());

                boolean studentOnCurs = false;
                if(serviceGradebook.isExist(student.getId())){
                    studentOnCurs = true;
                }
                session.setAttribute("studentOnCurs", studentOnCurs);

               // session.setAttribute("authUser", authUser);
                session.setAttribute("student", student);

                Curs javaCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypeJava));
                session.setAttribute("javaCurs", javaCurs);

                Curs phpCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypePHP));
                session.setAttribute("phpCurs", phpCurs);

                Curs cCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypeC));
                session.setAttribute("cCurs", cCurs);

                if(serviceCurs.getClassmates(serviceCurs.getCursId(langTypeJava)).contains(student)){
                    session.setAttribute("classmatesJava", serviceCurs.getClassmates(serviceCurs.getCursId(langTypeJava)));
                    session.setAttribute("java", "java");
                }
                if(serviceCurs.getClassmates(serviceCurs.getCursId(langTypePHP)).contains(student)){
                    session.setAttribute("classmatesPHP", serviceCurs.getClassmates(serviceCurs.getCursId(langTypePHP)));
                    session.setAttribute("php", "php");
                }
                if(serviceCurs.getClassmates(serviceCurs.getCursId(langTypeC)).contains(student)){
                    session.setAttribute("classmatesC", serviceCurs.getClassmates(serviceCurs.getCursId(langTypeC)));
                    session.setAttribute("cPlusPlus", "cPlusPlus");
                }
                log.info("student with email {} entry his personal area from start page", student.getEmail());
                return "StudentPersonalArea";

            } else if (role.equals(UserType.TEACHER)) {

                int page = 1;
                teacher = serviceTeacher.getTeacher(authUser.getUserId());
                curs = serviceCurs.getCurs(teacher.getCursId());
                groupDTO = serviceCurs.getMyStudents(teacher.getCursId(), page);
                int cursId = teacher.getCursId();
                int noOfRecords = serviceCurs.countOfStudents(cursId);
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 3);
                List<Teacher> teachers = serviceCurs.getColleagues(cursId);

                req.setAttribute("noOfPages", noOfPages);
                req.setAttribute("currentPage", page);
                session.setAttribute("students", groupDTO);
                session.setAttribute("teachers", teachers);
                session.setAttribute("teacher", teacher);
                //session.setAttribute("authUser", authUser);
                session.setAttribute("curs", curs);
                log.info("teacher with email {} entry his personal area from start page", teacher.getEmail());
                return "TeacherPersonalArea";
            }
        }else{
            return "InvalidData";
        }
        return "StartPage";
    }
}
