package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.service.ServiceTeacher;
import com.github.hodcko.multy.model.Teacher;
import org.springframework.transaction.annotation.Transactional;

public class ServiceTeacherDefault implements ServiceTeacher {

    private final DaoTeacher daoTeacher;

    public ServiceTeacherDefault(DaoTeacher daoTeacher) {
        this.daoTeacher = daoTeacher;
    }

    @Transactional
    @Override
    public Teacher saveTeacher(String name, String secondName, String email, int cursId){
        return daoTeacher.saveTeacher(name, secondName, email, cursId);
    }

    @Transactional
    @Override
    public Teacher getTeacher(int id){
        return daoTeacher.getTeacher(id);
    }

    @Transactional
    @Override
    public boolean deleteTeacher(String email) {
        return daoTeacher.deleteTeacher(email);
    }
}
