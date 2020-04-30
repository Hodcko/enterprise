package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.service.ServiceTeacher;
import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;
import com.github.hodcko.multy.model.Teacher;

public class ServiceTeacherDefault implements ServiceTeacher {

    private DaoTeacher daoTeacher = DaoTeacherDefault.getInstance();
    private static volatile ServiceTeacher instance;

    public static ServiceTeacher getInstance(){
        ServiceTeacher localInstance = instance;
        if(localInstance == null){
            synchronized (ServiceTeacher.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceTeacherDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Teacher saveTeacher(String name, String second_name, String email, int cursId){
        return daoTeacher.saveTeacher(name, second_name, email, cursId);
    }

    @Override
    public Teacher getTeacher(int id){
        return daoTeacher.getTeacher(id);
    }

    @Override
    public boolean deleteTeacher(String email) {
        return daoTeacher.deleteTeacher(email);
    }
}
