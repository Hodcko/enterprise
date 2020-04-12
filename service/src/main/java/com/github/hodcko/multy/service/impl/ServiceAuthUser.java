package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.impl.DaoUserAuth;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.dao.IDaoAuth;
import com.github.hodcko.multy.service.IServiceAuthUser;

public class ServiceAuthUser implements IServiceAuthUser {
    private IDaoAuth iDaoAuth = DaoUserAuth.getInstance();

    private static volatile IServiceAuthUser instance;

    public static IServiceAuthUser getInstance(){
        IServiceAuthUser localInstance = instance;
        if(localInstance == null){
            synchronized (IServiceAuthUser.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceAuthUser();
                }
            }
        }
        return localInstance;
    }

    @Override
    public AuthUser saveAuthUser(int user_id, String login, String password, String role){
        return iDaoAuth.saveAuthUser(user_id, login, password, role);
    }

    @Override
    public String getByLogin(String login){
        return iDaoAuth.getByLogin(login);
    }

    @Override
    public String passwordGenerate(String email, String userType){
        return iDaoAuth.passwordGenerate(email, userType);
    }

    @Override
    public boolean deleteAuthUser(int id, String role){
        return iDaoAuth.deleteAuthUser(id, role);
    }

    @Override
    public String getRole(String login, String password){
        return iDaoAuth.getRole(login, password);
    }

    @Override
    public AuthUser getAuthUser(String login, String password){
        return iDaoAuth.getAuthUser(login, password);
    }



}
