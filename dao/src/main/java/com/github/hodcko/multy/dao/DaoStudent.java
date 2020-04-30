package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.UserType;

public interface DaoStudent {

    Student saveStudent(String name, String second_name, String email, int age, int cursId);

    Student getStudent(int id);

    boolean deleteStudent(String email);

    boolean isExist(String email, UserType userType);

    int getId(String email, UserType userType);

    String passwordGenerate(String email, UserType userType);
}
