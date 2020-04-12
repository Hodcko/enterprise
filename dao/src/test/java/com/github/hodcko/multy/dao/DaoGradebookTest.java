package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoGetIdByEmail;
import com.github.hodcko.multy.dao.impl.DaoGradebook;
import com.github.hodcko.multy.model.Curs;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoGradebookTest {
    IDaoGradebook iDaoGradebook = DaoGradebook.getInstance();

    @Test
    void addStudentToGradebookTest() {
        int studentId = 1;
        int result = iDaoGradebook.addStudentToGradebook(studentId);
        assertEquals(1, result);
    }

    @Test
    void addGradeTest() {
        int studentId = 1;
        int result = iDaoGradebook.addGrade(studentId);
        assertEquals(1, result);
    }

    @Test
    void getGradeTest() {
        int studentId = 1;
        int result = iDaoGradebook.getGrade(studentId);
        assertEquals(1, result);
        iDaoGradebook.deleteStudentFromGradebook(studentId);
    }





}
