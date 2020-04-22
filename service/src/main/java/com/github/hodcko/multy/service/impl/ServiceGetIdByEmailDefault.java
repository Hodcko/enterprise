package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoGetIdByEmail;
import com.github.hodcko.multy.dao.impl.DaoGetIdByEmailDefault;
import com.github.hodcko.multy.model.UserType;

public class ServiceGetIdByEmailDefault implements com.github.hodcko.multy.service.ServiceGetIdByEmail {

    private DaoGetIdByEmail daoGetIdByEmail = DaoGetIdByEmailDefault.getInstance();
    private static volatile com.github.hodcko.multy.service.ServiceGetIdByEmail instance;

    public static com.github.hodcko.multy.service.ServiceGetIdByEmail getInstance(){
        com.github.hodcko.multy.service.ServiceGetIdByEmail localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.service.ServiceGetIdByEmail.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceGetIdByEmailDefault();
                }
            }
        }
        return localInstance;
    }

    public int getId(String email, UserType userType){
       return daoGetIdByEmail.getId(email, userType);
    }

}
