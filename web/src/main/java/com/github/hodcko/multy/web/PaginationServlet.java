package com.github.hodcko.multy.web;

import com.github.hodcko.multy.model.*;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.impl.ServiceCursDefault;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/pagination")
public class PaginationServlet extends HttpServlet {
    private ServiceCurs serviceCurs = ServiceCursDefault.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<DTOGroup> dtoGroup;

        int page = Integer.parseInt(req.getParameter("page"));
        int cursId = ((Teacher) session.getAttribute("teacher")).getCurs_id();
        int noOfRecords = serviceCurs.countOfStudents(cursId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 1);

        dtoGroup = serviceCurs.getMyStudents(cursId, page);

        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        session.setAttribute("students", dtoGroup);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/PersonalArea.jsp");
        dispatcher.forward(req, resp);
    }
}