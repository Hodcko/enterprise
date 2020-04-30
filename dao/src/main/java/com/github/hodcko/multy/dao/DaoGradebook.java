package com.github.hodcko.multy.dao;



public interface DaoGradebook {

    int addStudentToGradebook(int studentId);

    int addGrade(int studentId);

    int getGrade(int studentId);

    boolean deleteStudentFromGradebook(int studentId);

    boolean isExist(int studentId);
}
