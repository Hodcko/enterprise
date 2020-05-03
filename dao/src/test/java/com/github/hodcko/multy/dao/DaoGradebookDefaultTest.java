package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DaoGradebookDefaultTest {

    DaoGradebook daoGradebook = DaoGradebookDefault.getInstance();
    private int studentId = 1;
    private int cursId = 1;

    @Test
    void addStudentToGradebookTest() {
        int result = daoGradebook.addStudentToGradebook(studentId, cursId);
        daoGradebook.deleteStudentFromGradebook(studentId, cursId);
        assertEquals(studentId, result);
    }

    @Test
    void addGradeTest() {
        daoGradebook.addStudentToGradebook(studentId, cursId);
        int result = daoGradebook.addGrade(studentId, cursId);
        daoGradebook.deleteStudentFromGradebook(studentId, cursId);
        assertEquals(studentId, result);
    }

    @Test
    void getGradeTest() {
        daoGradebook.addStudentToGradebook(studentId, cursId);
        daoGradebook.addGrade(studentId, cursId);
        int result = daoGradebook.getGrade(studentId, cursId);
        assertEquals(1, result);
        daoGradebook.deleteStudentFromGradebook(studentId, cursId);
    }

    @Test
    void deleteStudentFromGradebookTest(){
        daoGradebook.addStudentToGradebook(studentId, cursId);
        daoGradebook.deleteStudentFromGradebook(studentId, cursId);
        assertFalse(daoGradebook.isExist(studentId));
    }

    @Test
    void isExistTest(){
        daoGradebook.addStudentToGradebook(studentId, cursId);
        assertTrue(daoGradebook.isExist(studentId));
        daoGradebook.deleteStudentFromGradebook(studentId, cursId);
    }
}
