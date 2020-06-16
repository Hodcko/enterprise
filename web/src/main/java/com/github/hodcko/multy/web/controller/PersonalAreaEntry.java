package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.dao.entity.GroupDTO;
import com.github.hodcko.multy.model.*;
import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceGetIdByEmail;
import com.github.hodcko.multy.service.ServiceGradebook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class PersonalAreaEntry {
    private static final Logger log = LoggerFactory.getLogger(PersonalAreaEntry.class);

    private final ServiceAuthUser saveAuthUser;
    private final ServiceGetIdByEmail getId;
    private final ServiceCurs serviceCurs;
    private final ServiceGradebook serviceGradebook;

    public PersonalAreaEntry(ServiceAuthUser saveAuthUser, ServiceGetIdByEmail getId, ServiceCurs serviceCurs, ServiceGradebook serviceGradebook) {
        this.saveAuthUser = saveAuthUser;
        this.getId = getId;
        this.serviceCurs = serviceCurs;
        this.serviceGradebook = serviceGradebook;
    }

    @PostMapping("/personal")
    public String enterAfterRegistration(HttpServletRequest req,  HttpSession session) {
        String email = (String) session.getAttribute("email");
        UserType userType = UserType.valueOf(session.getAttribute("userType").toString().toUpperCase());
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        AuthUser authUser;
        Curs curs;
        List<GroupDTO> groupDTO;

        if (userType.equals(UserType.STUDENT)) {
            Student student = ((Student) session.getAttribute("student"));

            String langTypeJava = (String) session.getAttribute("langTypeJava");
            String langTypePHP = (String) session.getAttribute("langTypePHP");
            String langTypeC = (String) session.getAttribute("langTypeC");

            authUser = saveAuthUser.saveAuthUser(getId.getId(email, userType), login, password, userType);

            if(langTypeJava != null){
                Curs javaCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypeJava));
                session.setAttribute("javaCurs", javaCurs);
                session.setAttribute("classmatesJava", serviceCurs.getClassmates(serviceCurs.getCursId(langTypeJava)));
            }
            if(langTypePHP != null){
                Curs phpCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypePHP));
                session.setAttribute("phpCurs", phpCurs);
                session.setAttribute("classmatesPHP", serviceCurs.getClassmates(serviceCurs.getCursId(langTypePHP)));
            }
            if(langTypeC != null){
                Curs cCurs = serviceCurs.getCurs(serviceCurs.getCursId(langTypeC));
                session.setAttribute("cCurs", cCurs);
                session.setAttribute("classmatesC", serviceCurs.getClassmates(serviceCurs.getCursId(langTypeC)));
            }
            session.setAttribute("studentOnCurs", serviceGradebook.isExist(((Student) session.getAttribute("student")).getId()));
            session.setAttribute("authUser", authUser);
            log.info("student with email {} entry his personal after registration", student.getEmail());

            return "StudentPersonalArea";

        } else if (userType.equals(UserType.TEACHER)) {
            Teacher teacher = ((Teacher) session.getAttribute("teacher"));
            int cursId = teacher.getCursId();
            int page = 1;
            int numberOfRecordsOnPage = 3;
            int noOfRecords = serviceCurs.countOfStudents(cursId);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / numberOfRecordsOnPage);

            authUser = saveAuthUser.saveAuthUser(teacher.getId(), teacher.getName(),
                    saveAuthUser.passwordGenerate(teacher.getEmail(), userType), userType);
            curs = serviceCurs.getCurs(cursId);
            groupDTO = serviceCurs.getMyStudents(cursId, page);
            List<Teacher> teachers = serviceCurs.getColleagues(cursId);

            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            session.setAttribute("students", groupDTO);
            session.setAttribute("teachers", teachers);
            session.setAttribute("authUser", authUser);
            session.setAttribute("curs", curs);
            log.info("teacher with email {} entry his personal after registration", teacher.getEmail());

            return "TeacherPersonalArea";

        } else {
            return "InvalidData";
        }
    }
}
