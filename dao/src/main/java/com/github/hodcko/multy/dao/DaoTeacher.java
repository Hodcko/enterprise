package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;

public interface DaoTeacher {

    Teacher saveTeacher(String name, String second_name, String email, int curs_id);

    Teacher getTeacher(int id);

    boolean deleteTeacher(String email);

    boolean isExist(String email, UserType userType);

    int getId(String email, UserType userType);

    String passwordGenerate(String email, UserType userType);

}
