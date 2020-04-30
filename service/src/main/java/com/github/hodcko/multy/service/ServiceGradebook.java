package com.github.hodcko.multy.service;



public interface ServiceGradebook {

    int addStudentToGradebook(int studentId);

    int addGrade(int studentId);

    int getGrade(int studentId);

    int checkTest(int studentId, String first, String second, String third, String fourth, String fifth);

    boolean isExist(int studentId);

    boolean deleteStudentFromGradebook(int studentId);


}
