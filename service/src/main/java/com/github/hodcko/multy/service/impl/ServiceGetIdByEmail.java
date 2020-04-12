package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.IDaoGetIdByEmail;
import com.github.hodcko.multy.service.IServiceGetIdByEmail;
import com.github.hodcko.multy.dao.impl.DaoGetIdByEmail;

public class ServiceGetIdByEmail implements IServiceGetIdByEmail {

    private IDaoGetIdByEmail iDaoGetIdByEmail = DaoGetIdByEmail.getInstance();
    private static volatile IServiceGetIdByEmail instance;

    public static IServiceGetIdByEmail getInstance(){
        IServiceGetIdByEmail localInstance = instance;
        if(localInstance == null){
            synchronized (IServiceGetIdByEmail.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceGetIdByEmail();
                }
            }
        }
        return localInstance;
    }

    public int getId(String email, String userType){
       return iDaoGetIdByEmail.getId(email, userType);
    }

}
