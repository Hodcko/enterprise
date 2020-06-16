package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.Student;


public interface DaoStudent {

    Student saveStudent(String name, String second_name, String email, int age);

    Student getStudent(int id);

    boolean deleteStudent(String email);

    boolean isExist(String email);

    int getId(String email);

    String passwordGenerate(String email);
}
