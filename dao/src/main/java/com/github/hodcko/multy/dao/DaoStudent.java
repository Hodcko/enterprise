package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.Student;

public interface DaoStudent {

    Student saveStudent(String name, String second_name, String email, int age, int curs_id);

    Student getStudent(int id);

    boolean deleteStudent(String email);
}
