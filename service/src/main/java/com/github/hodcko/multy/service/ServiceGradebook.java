package com.github.hodcko.multy.service;



public interface ServiceGradebook {

    int addStudentToGradebook(int studentId, int cursId);

    int addGrade(int studentId, int cursId);

    int getGrade(int studentId, int cursId);

    int checkTest(int studentId, int cursId, String first, String second, String third, String fourth, String fifth);

    int checkTestJava(int studentId, int cursId, String first, String second, String third, String fourth, String fifth);

    int checkTestCPlusPlus(int studentId, int cursId, String first, String second, String third, String fourth, String fifth);

    int checkTestPHP(int studentId, int cursId, String first, String second, String third, String fourth, String fifth);

    boolean isExist(int studentId);

    boolean deleteStudentFromGradebook(int studentId);


}
