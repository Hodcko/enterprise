package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.model.UserType;
import org.springframework.transaction.annotation.Transactional;

public class ServiceGetIdByEmailDefault implements com.github.hodcko.multy.service.ServiceGetIdByEmail {

    private final DaoTeacher daoTeacher;
    private final DaoStudent daoStudent;

    public ServiceGetIdByEmailDefault(DaoTeacher daoTeacher, DaoStudent daoStudent) {
        this.daoTeacher = daoTeacher;
        this.daoStudent = daoStudent;
    }

    @Transactional
    public int getId(String email, UserType userType){
       if(userType.equals(UserType.STUDENT)){
           return daoStudent.getId(email, userType);
       }else{
           return daoTeacher.getId(email, userType);
       }
    }

}
