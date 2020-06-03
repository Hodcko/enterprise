package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.service.ServiceStudent;
import com.github.hodcko.multy.model.Student;
import org.springframework.transaction.annotation.Transactional;

public class ServiceStudentDefault implements ServiceStudent {

    private final  DaoStudent daoStudent;

    public ServiceStudentDefault(DaoStudent daoStudent) {
        this.daoStudent = daoStudent;
    }

    @Transactional
    @Override
    public Student saveStudent(String name, String second_name, String email, int age){
       return daoStudent.saveStudent(name, second_name, email, age);
    }

    @Transactional
    @Override
    public Student getStudent(int id){
        return  daoStudent.getStudent(id);
    }

    @Transactional
    @Override
    public boolean deleteStudent(String email) {
        return daoStudent.deleteStudent(email);
    }
}
