package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Teacher;

public interface IDaoTeacher {

    Teacher saveTeacher(String name, String second_name, String email, int curs_id);

    Teacher getTeacher(int id);

    boolean deleteTeacher(String email);

}
