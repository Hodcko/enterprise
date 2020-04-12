package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.IDaoAuth;
import com.github.hodcko.multy.service.ISecurityService;
import com.github.hodcko.multy.dao.impl.DaoUserAuth;

public class ServiceAuthUserLogin implements ISecurityService {
    private IDaoAuth iDaoAuth = DaoUserAuth.getInstance();

    private static volatile ISecurityService instance;

    public static ISecurityService getInstance() {
        ISecurityService localInstance = instance;
        if (localInstance == null) {
            synchronized (ISecurityService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ServiceAuthUserLogin();
                }
            }
        }
        return localInstance;
    }

    @Override
    public String login(String login, String password){
        String user_login = iDaoAuth.getByLogin(password);
        if ( user_login == null) {
            return null;
        }
        if (user_login.equals(login)) {
            return login;
        }
        return null;
    }

    @Override
    public boolean changePassword(String login, String password, String newPassword){
        return iDaoAuth.changePassword(login, password , newPassword);
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
