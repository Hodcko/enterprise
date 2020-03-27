package com.hodcko.depservlet;

import com.hodcko.depservlet.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SessionCart", urlPatterns = "/sessioncart")
public class SessionCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        Cart cart = (Cart)session.getAttribute("cart");

        String name = req.getParameter("name");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        if(cart == null){
            cart = new Cart();
            cart.setName(name);
            cart.setQuantity(quantity);
        }

        session.setAttribute("cart", cart);

        PrintWriter pw = resp.getWriter();
        pw.write(cart.getName() + " " + cart.getQuantity());


    }
}
