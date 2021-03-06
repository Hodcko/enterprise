package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.dao.entity.GroupDTO;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.service.ServiceCurs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class Pagination {
    private final ServiceCurs serviceCurs;

    public Pagination(ServiceCurs serviceCurs) {
        this.serviceCurs = serviceCurs;
    }

    @GetMapping("/pagination")
    public String pagination(HttpServletRequest req, HttpSession session) {
        List<GroupDTO> groupDTO;
        int numberOfRecordsOnPage = 3;

        int page = Integer.parseInt(req.getParameter("page"));
        int cursId = ((Teacher) session.getAttribute("teacher")).getCursId();
        int noOfRecords = serviceCurs.countOfStudents(cursId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / numberOfRecordsOnPage);

        groupDTO = serviceCurs.getMyStudents(cursId, page);

        session.setAttribute("noOfPages", noOfPages);
        session.setAttribute("currentPage", page);
        session.setAttribute("students", groupDTO);

        return "TeacherPersonalArea";

    }
}
