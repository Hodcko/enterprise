package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.IDaoTeacher;
import com.github.hodcko.multy.service.IServiceTeacher;
import com.github.hodcko.multy.dao.impl.DaoTeacherManager;
import com.github.hodcko.multy.model.Teacher;

public class ServiceTeacherManager implements IServiceTeacher {

    private IDaoTeacher iDaoTeacher = DaoTeacherManager.getInstance();
    private static volatile IServiceTeacher instance;

    public static IServiceTeacher getInstance(){
        IServiceTeacher localInstance = instance;
        if(localInstance == null){
            synchronized (IServiceTeacher.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceTeacherManager();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Teacher saveTeacher(String name, String second_name, String email, int curs_id){
        return iDaoTeacher.saveTeacher(name, second_name, email, curs_id);
    }

    @Override
    public Teacher getTeacher(int id){
        return iDaoTeacher.getTeacher(id);
    }

}
