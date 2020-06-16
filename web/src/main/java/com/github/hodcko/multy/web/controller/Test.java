package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceGradebook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class Test {
    private static final Logger log = LoggerFactory.getLogger(Test.class);


    private final ServiceGradebook serviceGradebook;
    private final ServiceCurs serviceCurs;

    public Test(ServiceGradebook serviceGradebook, ServiceCurs serviceCurs) {
        this.serviceGradebook = serviceGradebook;
        this.serviceCurs = serviceCurs;
    }

    @GetMapping("/javaTest")
    public String javaTest() {
        return "JavaTestPage";
    }

    @GetMapping("/phpTest")
    public String phpTest() {
        return "PHPTestPage";
    }

    @GetMapping("/cTest")
    public String cTest() {
        return "CTestPage";
    }


    @PostMapping("/test")
    public String testChecker(HttpServletRequest req, HttpSession session) {
        Student student = (Student)session.getAttribute("student");
        String langType = req.getParameter("test");
        int cursId = serviceCurs.getCursId(langType);

        String firstQ = req.getParameter("first");
        String secondQ = req.getParameter("second");
        String thirdQ = req.getParameter("third");
        String fourthQ = req.getParameter("fourth");
        String fifthQ = req.getParameter("fifth");

        int resultOfTest = serviceGradebook.checkTest(student.getId(), cursId, firstQ, secondQ, thirdQ, fourthQ, fifthQ);

        session.setAttribute("result", resultOfTest);

        log.info("student with email {} got a grade {} for the test {} ", student.getEmail(), resultOfTest, langType);

        return "ResultOfTest";

    }

}
