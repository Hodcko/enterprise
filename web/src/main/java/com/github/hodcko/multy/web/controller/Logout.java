package com.github.hodcko.multy.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping
public class Logout {

    @GetMapping("/customLogout")
    public String logout() {
        SecurityContextHolder.clearContext();
//        req.getSession().invalidate();
        return "StartPage";
    }
}
