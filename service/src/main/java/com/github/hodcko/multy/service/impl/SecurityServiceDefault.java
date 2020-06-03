package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoAuthUser;
import com.github.hodcko.multy.service.SecurityService;
import org.springframework.transaction.annotation.Transactional;

public class SecurityServiceDefault implements SecurityService {

    private final DaoAuthUser daoAuthUser;

    public SecurityServiceDefault(DaoAuthUser daoAuthUser) {
        this.daoAuthUser = daoAuthUser;
    }

    @Transactional
    @Override
    public String login(String login, String password){
        String userLogin = daoAuthUser.getLoginByPassword(password);
        if (userLogin == null) {
            return null;
        }
        if (userLogin.equals(login)) {
            return login;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean changePassword(String login, String password, String newPassword){
        return daoAuthUser.changePassword(login, password , newPassword);
    }

    @Transactional
    @Override
    public String findPassword(String password, String anotherPassword){
        if(password == null){
            return anotherPassword;
        }else {
            return password;
        }
    }
}
