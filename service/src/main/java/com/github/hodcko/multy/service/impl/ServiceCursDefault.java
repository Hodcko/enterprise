package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.GroupDTO;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public class ServiceCursDefault implements com.github.hodcko.multy.service.ServiceCurs {
    private final DaoCurs daoCurs;

    public ServiceCursDefault(DaoCurs daoCurs) {
        this.daoCurs = daoCurs;
    }

    @Transactional
    @Override
    public Curs createCurs(String name, LocalDate start, LocalDate end){
        return daoCurs.createCurs(name, start, end);
    }

    @Transactional
    @Override
    public int getCursId(String langType) {
        if (langType.equalsIgnoreCase("java")){
            return 1;
        }else if(langType.equalsIgnoreCase("php")){
            return 2;
        }else if(langType.equalsIgnoreCase("c++")){
            return 3;
        }
        return 0;
    }

    @Transactional
    @Override
    public Curs getCurs(int cursId){
        return daoCurs.getCurs(cursId);
    }

    @Transactional
    @Override
    public List<GroupDTO> getMyStudents(int cursId, int numPage) {
        return daoCurs.getMyStudents(cursId, numPage);
    }

    @Transactional
    @Override
    public int countOfStudents(int cursId){
        return daoCurs.countOfStudents(cursId);
    }

    @Transactional
    @Override
    public List<Student> getClassmates(int cursId){
        return daoCurs.getClassmates(cursId);
    }

    @Transactional
    @Override
    public boolean deleteCurs(int cursId) {
        return daoCurs.deleteCurs(cursId);
    }

    @Transactional
    @Override
    public boolean inviteStudentOnCurs(int studentId, int cursId){
        return daoCurs.inviteStudentOnCurs(studentId, cursId);
    }

    @Transactional
    @Override
    public List<Teacher> getColleagues(int cursId){
        return daoCurs.getColleagues(cursId);
    }
}
