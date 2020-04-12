package com.github.hodcko.multy.service;


import com.github.hodcko.multy.model.Student;

public interface IServiceStudent {

    Student saveStudent(String name, String second_name, String email, int age, int curs_id);

    Student getStudent(int id);
}
