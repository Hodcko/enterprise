package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.service.IServiceStudent;
import com.github.hodcko.multy.dao.impl.DaoStudentManager;
import com.github.hodcko.multy.model.Student;

public class ServiceStudentManager implements IServiceStudent {

    private com.github.hodcko.multy.dao.IDaoStudent IDaoStudent = DaoStudentManager.getInstance();

    private static volatile IServiceStudent instance;

    public static IServiceStudent getInstance(){
        IServiceStudent localInstance = instance;
        if(localInstance == null){
            synchronized (IServiceStudent.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceStudentManager();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Student saveStudent(String name, String second_name, String email, int age, int curs_id){
       return IDaoStudent.saveStudent(name, second_name, email, age, curs_id);
    }

    @Override
    public Student getStudent(int id){
        return  IDaoStudent.getStudent(id);
    }



}
