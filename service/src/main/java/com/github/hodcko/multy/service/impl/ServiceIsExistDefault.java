package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;
import com.github.hodcko.multy.model.UserType;

public class ServiceIsExistDefault implements com.github.hodcko.multy.service.ServiceIsExist {

    private static volatile com.github.hodcko.multy.service.ServiceIsExist instance;
    private DaoStudent daoStudent = DaoStudentDefault.getInstance();
    private DaoTeacher daoTeacher = DaoTeacherDefault.getInstance();



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

    public boolean isExist(String email, UserType userType){
        if(userType.equals(UserType.STUDENT)){
            return daoStudent.isExist(email, userType);
        }else {
            return daoTeacher.isExist(email, userType);
        }
    }

}
