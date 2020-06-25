package com.github.hodcko.multy.web.controller;

import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping
public class ChangePassword {
    private static final Logger log = LoggerFactory.getLogger(ChangePassword.class);
    private final SecurityService securityService;

    public ChangePassword(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/change")
    public String doPost(HttpServletRequest req) {

        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String newPassword = req.getParameter("newPassword");
        String newPasswords = req.getParameter("newPasswords");
        String rightPassword = securityService.findPassword(newPassword, newPasswords);
        if(securityService.changePassword(authUser.getLogin(), authUser.getPassword(), rightPassword )){
            log.info("user {} changed password to {}", authUser.getLogin(), newPassword);
            return "LoginFromStartPage";

        }else {
            return "InvalidData";
        }
    }
}
