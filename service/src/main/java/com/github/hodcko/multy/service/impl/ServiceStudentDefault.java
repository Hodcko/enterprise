package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.service.ServiceStudent;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.model.Student;

public class ServiceStudentDefault implements ServiceStudent {

    private DaoStudent DaoStudent = DaoStudentDefault.getInstance();

    private static volatile ServiceStudent instance;

    public static ServiceStudent getInstance(){
        ServiceStudent localInstance = instance;
        if(localInstance == null){
            synchronized (ServiceStudent.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceStudentDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Student saveStudent(String name, String second_name, String email, int age, int curs_id){
       return DaoStudent.saveStudent(name, second_name, email, age, curs_id);
    }

    @Override
    public Student getStudent(int id){
        return  DaoStudent.getStudent(id);
    }



}
