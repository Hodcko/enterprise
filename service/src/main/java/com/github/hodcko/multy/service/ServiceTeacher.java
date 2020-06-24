package com.github.hodcko.multy.service;

import com.github.hodcko.multy.model.Teacher;

public interface ServiceTeacher {

    Teacher saveTeacher(String name, String secondName, String email, int cursId);

    Teacher getTeacher(int id);

    boolean deleteTeacher(String email);

}
