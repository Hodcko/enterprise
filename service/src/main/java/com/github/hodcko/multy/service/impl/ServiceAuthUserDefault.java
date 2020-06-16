package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.dao.DaoAuthUser;
import com.github.hodcko.multy.model.UserType;
import org.springframework.transaction.annotation.Transactional;

public class ServiceAuthUserDefault implements com.github.hodcko.multy.service.ServiceAuthUser {

    private final DaoAuthUser daoAuthUser;
    private final DaoStudent daoStudent;
    private final DaoTeacher daoTeacher;

    public ServiceAuthUserDefault(DaoAuthUser daoAuthUser, DaoStudent daoStudent, DaoTeacher daoTeacher) {
        this.daoAuthUser = daoAuthUser;
        this.daoStudent = daoStudent;
        this.daoTeacher = daoTeacher;
    }

    @Transactional
    @Override
    public AuthUser saveAuthUser(int userId, String login, String password, UserType role){
        return daoAuthUser.saveAuthUser(userId, login, password, role);
    }

    @Transactional
    @Override
    public String getLoginByPassword(String password){
        return daoAuthUser.getLoginByPassword(password);
    }

    @Transactional
    @Override
    public String passwordGenerate(String email, UserType userType){
        if(userType.equals(UserType.STUDENT)){
            return daoStudent.passwordGenerate(email);
        }else {
            return daoTeacher.passwordGenerate(email);
        }
    }

    @Transactional
    @Override
    public boolean deleteAuthUser(int id, UserType role){
        return daoAuthUser.deleteAuthUser(id, role);
    }

    @Transactional
    @Override
    public UserType getRole(String login, String password){
        return daoAuthUser.getRole(login, password);
    }

    @Transactional
    @Override
    public AuthUser getAuthUser(String login, String password){
        return daoAuthUser.getAuthUser(login, password);
    }
}
