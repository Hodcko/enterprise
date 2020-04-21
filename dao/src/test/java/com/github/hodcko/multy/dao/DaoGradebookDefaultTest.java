package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoGradebookDefaultTest {
    DaoGradebook daoGradebook = DaoGradebookDefault.getInstance();

    @Test
    void addStudentToGradebookTest() {
        int studentId = 1;
        int result = daoGradebook.addStudentToGradebook(studentId);
        assertEquals(1, result);
    }

    @Test
    void addGradeTest() {
        int studentId = 1;
        int result = daoGradebook.addGrade(studentId);
        assertEquals(1, result);
    }

    @Test
    void getGradeTest() {
        int studentId = 1;
        int result = daoGradebook.getGrade(studentId);
        assertEquals(1, result);
        daoGradebook.deleteStudentFromGradebook(studentId);
    }





}
