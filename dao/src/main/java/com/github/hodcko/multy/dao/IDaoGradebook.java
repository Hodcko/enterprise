package com.github.hodcko.multy.dao;



public interface IDaoGradebook {

    int addStudentToGradebook(int studetn_id);

    int addGrade(int studetn_id);

    int getGrade(int studetn_id);

    boolean deleteStudentFromGradebook(int student_id);

}
