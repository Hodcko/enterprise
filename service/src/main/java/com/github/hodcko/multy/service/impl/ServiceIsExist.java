package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.dao.IDaoIsExist;
import com.github.hodcko.multy.service.IServiceIsExist;
import com.github.hodcko.multy.dao.impl.DaoIsExist;

public class ServiceIsExist implements IServiceIsExist{

    private static volatile IServiceIsExist instance;
    private IDaoIsExist iDaoIsExist = DaoIsExist.getInstance();


    public static IServiceIsExist getInstance(){
        IServiceIsExist localInstance = instance;
        if(localInstance == null){
            synchronized (IServiceIsExist.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceIsExist();
                }
            }
        }
        return localInstance;
    }

    public boolean isExist(String email, String userType){
        return iDaoIsExist.isExist(email, userType);
    }

}
