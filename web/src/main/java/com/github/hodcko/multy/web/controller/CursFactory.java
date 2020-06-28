package com.github.hodcko.multy.web.controller;


import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceGradebook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final ServiceGradebook serviceGradebook;

    public CursFactory(ServiceCurs serviceCurs, ServiceAuthUser serviceAuthUser, ServiceGradebook serviceGradebook) {
        this.serviceCurs = serviceCurs;
        this.serviceAuthUser = serviceAuthUser;
        this.serviceGradebook = serviceGradebook;
    }


    @PostMapping("/curs")
    public String createCurs(HttpServletRequest req) {
        String cursName = req.getParameter("name");
        LocalDate startDate = Date.valueOf(req.getParameter("startDate")).toLocalDate();
        LocalDate endDate = Date.valueOf(req.getParameter("endDate")).toLocalDate();
        serviceCurs.createCurs(cursName, startDate, endDate);
        log.info("created curs {} with start date {} and end date {}", cursName, startDate, endDate);
        return "forward:/clean";
    }

    @GetMapping("/escape")
    public String endStudy(HttpServletRequest req, HttpSession session) {
        if(req.getParameter("escape").equalsIgnoreCase("escape")){
            if(session.getAttribute("student") != null){
                Student student = (Student) session.getAttribute("student");
                serviceAuthUser.deleteAuthUser(student.getId(), UserType.STUDENT);
                serviceGradebook.deleteStudentFromGradebook(student.getId());
                log.info("student with email {} escaped curs", student.getEmail());
            }
            if (session.getAttribute("teacher") != null){
                Teacher teacher = (Teacher) session.getAttribute("teacher");
                serviceAuthUser.deleteAuthUser(teacher.getId(), UserType.TEACHER);
                log.info("teacher with email {} escaped curs", teacher.getEmail());
            }
            req.getSession().invalidate();
            SecurityContextHolder.clearContext();
            return "StartPage";
        }
        return "StartPage";
    }
}
