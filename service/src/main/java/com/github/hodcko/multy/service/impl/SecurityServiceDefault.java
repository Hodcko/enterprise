package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoAuthUser;
import com.github.hodcko.multy.service.SecurityService;
import com.github.hodcko.multy.dao.impl.DaoAuthUserDefault;

public class SecurityServiceDefault implements SecurityService {

    private DaoAuthUser daoAuthUser = DaoAuthUserDefault.getInstance();
    private static volatile SecurityService instance;

    public static SecurityService getInstance() {
        SecurityService localInstance = instance;
        if (localInstance == null) {
            synchronized (SecurityService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SecurityServiceDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public String login(String login, String password){
        String user_login = daoAuthUser.getByLogin(password);
        if (user_login == null) {
            return null;
        }
        if (user_login.equals(login)) {
            return login;
        }
        return null;
    }

    @Override
    public boolean changePassword(String login, String password, String newPassword){
        return daoAuthUser.changePassword(login, password , newPassword);
    }

    @Override
    public String findPassword(String password, String anotherPassword){
        if(password == null){
            return anotherPassword;
        }else {
            return password;
        }
    }
}
