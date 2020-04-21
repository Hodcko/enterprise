package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.dao.DaoIsExist;
import com.github.hodcko.multy.dao.impl.DaoIsExistDefault;

public class ServiceIsExistDefault implements com.github.hodcko.multy.service.ServiceIsExist {

    private static volatile com.github.hodcko.multy.service.ServiceIsExist instance;
    private DaoIsExist daoIsExist = DaoIsExistDefault.getInstance();


    public static com.github.hodcko.multy.service.ServiceIsExist getInstance(){
        com.github.hodcko.multy.service.ServiceIsExist localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.service.ServiceIsExist.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceIsExistDefault();
                }
            }
        }
        return localInstance;
    }

    public boolean isExist(String email, String userType){
        return daoIsExist.isExist(email, userType);
    }

}
