package com.github.hodcko.multy.service;

import com.github.hodcko.multy.model.Teacher;

public interface IServiceTeacher {
    Teacher saveTeacher(String name, String second_name, String email, int curs_id);

    Teacher getTeacher(int id);


}
