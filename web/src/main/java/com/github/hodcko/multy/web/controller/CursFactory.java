package com.github.hodcko.multy.web.controller;


import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.ServiceCurs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping
public class CursFactory {
    private static final Logger log = LoggerFactory.getLogger(CursFactory.class);

    private final ServiceCurs serviceCurs;
    private final ServiceAuthUser serviceAuthUser;

    public CursFactory(ServiceCurs serviceCurs, ServiceAuthUser serviceAuthUser) {
        this.serviceCurs = serviceCurs;
        this.serviceAuthUser = serviceAuthUser;
    }

    @PostMapping("/curs")
    public String createCurs(HttpServletRequest req) {
        String cursName = req.getParameter("name");
        LocalDate startDate = Date.valueOf(req.getParameter("startDate")).toLocalDate();
        LocalDate endDate = Date.valueOf(req.getParameter("endDate")).toLocalDate();
        serviceCurs.createCurs(cursName, startDate, endDate);
        log.info("created curs {} with start date {} and end date {}", cursName, startDate, endDate);
        return "redirect:/personal";

    }


    @GetMapping("/escape")
    public String endStudy(HttpServletRequest req, HttpSession session) {
        if(req.getParameter("escape").equalsIgnoreCase("escape")){
            if(session.getAttribute("student") != null){
                Student student = (Student) session.getAttribute("student");
                serviceAuthUser.deleteAuthUser(student.getId(), UserType.STUDENT);
                log.info("student with email {} escaped curs", student.getEmail());
            }
            if (session.getAttribute("teacher") != null){
                Teacher teacher = (Teacher) session.getAttribute("teacher");
                serviceAuthUser.deleteAuthUser(teacher.getId(), UserType.TEACHER);
                log.info("teacher with email {} escaped curs", teacher.getEmail());
            }
            req.getSession().invalidate();
            return "StartPage";
        }
        return "StartPage";
    }




}
