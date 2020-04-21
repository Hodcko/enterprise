package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.impl.DaoAuthUserUserDefault;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.dao.DaoAuthUser;

public class ServiceAuthUserDefault implements com.github.hodcko.multy.service.ServiceAuthUser {

    private DaoAuthUser daoAuthUser = DaoAuthUserUserDefault.getInstance();
    private static volatile com.github.hodcko.multy.service.ServiceAuthUser instance;

    public static com.github.hodcko.multy.service.ServiceAuthUser getInstance(){
        com.github.hodcko.multy.service.ServiceAuthUser localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.service.ServiceAuthUser.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceAuthUserDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public AuthUser saveAuthUser(int user_id, String login, String password, String role){
        return daoAuthUser.saveAuthUser(user_id, login, password, role);
    }

    @Override
    public String getByLogin(String login){
        return daoAuthUser.getByLogin(login);
    }

    @Override
    public String passwordGenerate(String email, String userType){
        return daoAuthUser.passwordGenerate(email, userType);
    }

    @Override
    public boolean deleteAuthUser(int id, String role){
        return daoAuthUser.deleteAuthUser(id, role);
    }

    @Override
    public String getRole(String login, String password){
        return daoAuthUser.getRole(login, password);
    }

    @Override
    public AuthUser getAuthUser(String login, String password){
        return daoAuthUser.getAuthUser(login, password);
    }



}
