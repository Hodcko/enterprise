package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Teacher;


public interface DaoTeacher {

    Teacher saveTeacher(String name, String second_name, String email, int cursId);

    Teacher getTeacher(int id);

    boolean deleteTeacher(String email);

    boolean isExist(String email);

    int getId(String email);

    String passwordGenerate(String email);

}
