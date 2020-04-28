package com.github.hodcko.multy.service;



public interface ServiceGradebook {

    int addStudentToGradebook(int studetn_id);

    int addGrade(int studetn_id);

    int getGrade(int studetn_id);

    int checkTest(int student_id, String first, String second, String third, String fourth, String fifth);

    boolean isExist(int student_id);

    boolean deleteStudentFromGradebook(int student_id);


}
