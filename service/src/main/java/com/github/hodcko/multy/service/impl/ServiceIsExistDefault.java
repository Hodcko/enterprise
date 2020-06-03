package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;
import com.github.hodcko.multy.model.UserType;
import org.springframework.transaction.annotation.Transactional;

public class ServiceIsExistDefault implements com.github.hodcko.multy.service.ServiceIsExist {


    private final DaoStudent daoStudent;
    private final DaoTeacher daoTeacher;

    public ServiceIsExistDefault(DaoStudent daoStudent, DaoTeacher daoTeacher) {
        this.daoStudent = daoStudent;
        this.daoTeacher = daoTeacher;
    }


    @Transactional
    public boolean isExist(String email, UserType userType){
        if(userType.equals(UserType.STUDENT)){
            return daoStudent.isExist(email, userType);
        }else {
            return daoTeacher.isExist(email, userType);
        }
    }

}
