package com.github.hodcko.multy.dao;



public interface DaoGradebook {

    int addStudentToGradebook(int studentId, int cursId);

    int addGrade(int studentId, int cursId);

    int getGrade(int studentId, int cursId);

    boolean deleteStudentFromGradebook(int studentId, int cursId);

    boolean isExist(int studentId);
}
