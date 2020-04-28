package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;
import com.github.hodcko.multy.model.UserType;

public class ServiceGetIdByEmailDefault implements com.github.hodcko.multy.service.ServiceGetIdByEmail {

    private DaoTeacher daoTeacher = DaoTeacherDefault.getInstance();
    private DaoStudent daoStudent = DaoStudentDefault.getInstance();
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
       if(userType.equals(UserType.STUDENT)){
           return daoStudent.getId(email, userType);
       }else{
           return daoTeacher.getId(email, userType);
       }
    }

}
