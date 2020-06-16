package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.model.AuthUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class Cleaner {

    @PostMapping("/clean")
    public String cleanSession(HttpServletRequest req, HttpSession session) {
        AuthUser authUser = (AuthUser)session.getAttribute("authUser");
        session.invalidate();
        req.setAttribute("login", authUser.getLogin());
        req.setAttribute("password", authUser.getPassword());
        return "forward:/personalStart";

    }



}
