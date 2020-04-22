package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoGradebookDefaultTest {
    DaoGradebook daoGradebook = DaoGradebookDefault.getInstance();
    private int studentId = 1;

    @Test
    void addStudentToGradebookTest() {
        int result = daoGradebook.addStudentToGradebook(studentId);
        daoGradebook.deleteStudentFromGradebook(studentId);
        assertEquals(studentId, result);
    }

    @Test
    void addGradeTest() {
        daoGradebook.addStudentToGradebook(studentId);
        int result = daoGradebook.addGrade(studentId);
        daoGradebook.deleteStudentFromGradebook(studentId);
        assertEquals(studentId, result);
    }

    @Test
    void getGradeTest() {
        daoGradebook.addStudentToGradebook(studentId);
        daoGradebook.addGrade(studentId);
        int result = daoGradebook.getGrade(studentId);
        assertEquals(1, result);
        daoGradebook.deleteStudentFromGradebook(studentId);
    }
}
