package com.hodcko;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/salary")
public class Salary extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CalculationOfDeductions calc = new CalculationOfDeductions();

        calc.setSalary(Double.parseDouble(req.getParameter("salary")));
        calc.setFszn(Double.parseDouble(req.getParameter("fszn")));
        calc.setInsurance(Double.parseDouble(req.getParameter("insurance")));
        calc.setIncomeTax(Double.parseDouble(req.getParameter("incomeTax")));
        calc.setUnoinDeductions(Double.parseDouble(req.getParameter("unoinDeductions")));
        calc.setAmountToBePaid();

        req.setAttribute("salary", calc);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/payslip.jsp");
        dispatcher.forward(req, resp);

    }
}
