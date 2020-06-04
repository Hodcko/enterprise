package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceGradebook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class Study {
    private static final Logger log = LoggerFactory.getLogger(Study.class);

    private final ServiceGradebook serviceGradebook;
    private final ServiceCurs serviceCurs;

    public Study(ServiceGradebook serviceGradebook, ServiceCurs serviceCurs) {
        this.serviceGradebook = serviceGradebook;
        this.serviceCurs = serviceCurs;
    }



    @PostMapping("/study")
    public String doPost(HttpServletRequest req) {
        String javaReg = (req.getParameter("javaReg"));
        String phpReg = (req.getParameter("phpReg"));
        String cReg = (req.getParameter("cReg"));

        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("student");

        session.setAttribute("studentOnCurs", serviceGradebook.isExist(student.getId()));

        if (javaReg != null) {
            serviceCurs.inviteStudentOnCurs(student.getId(), serviceCurs.getCursId(javaReg));
            serviceGradebook.addStudentToGradebook(student.getId(), serviceCurs.getCursId(javaReg));
            session.setAttribute("java", "java");
            log.info("student with email {} go on java ", student.getEmail());
        }

        if (phpReg != null) {
            serviceCurs.inviteStudentOnCurs(student.getId(), serviceCurs.getCursId(phpReg));
            serviceGradebook.addStudentToGradebook(student.getId(), serviceCurs.getCursId(phpReg));
            session.setAttribute("php", "php");
            log.info("student with email {} go on php ", student.getEmail());

        }

        if (cReg != null) {
            serviceCurs.inviteStudentOnCurs(student.getId(), serviceCurs.getCursId(cReg));
            serviceGradebook.addStudentToGradebook(student.getId(), serviceCurs.getCursId(cReg));
            session.setAttribute("cPlusPlus", "cPlusPlus");
            log.info("student with email {} go on c++ ", student.getEmail());

        }

        return "forward:/StudyPage.jsp";
    }
}
