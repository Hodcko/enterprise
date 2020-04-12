package com.github.hodcko.multy.web;

import com.github.hodcko.multy.service.IServiceCurs;
import com.github.hodcko.multy.service.impl.ServiceCurs;
import com.github.hodcko.multy.model.Curs;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/curs")
public class CreateCursServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cursName = req.getParameter("name");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));

        IServiceCurs iServiceCurs = ServiceCurs.getInstance();

        iServiceCurs.createCurs(cursName, startDate, endDate);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/personal");
        dispatcher.forward(req, resp);

    }
}
