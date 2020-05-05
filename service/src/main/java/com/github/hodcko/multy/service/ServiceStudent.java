package com.github.hodcko.multy.service;


import com.github.hodcko.multy.model.Student;

public interface ServiceStudent {

    Student saveStudent(String name, String second_name, String email, int age);

    Student getStudent(int id);

    boolean deleteStudent(String email);

}
