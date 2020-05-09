package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.model.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DaoGradebookDefaultTest {

    DaoGradebook daoGradebook = DaoGradebookDefault.getInstance();
    static DaoStudent daoStudent = DaoStudentDefault.getInstance();
    private int cursId = 1;
    static Student student;

    @BeforeAll
    static void addStudent(){
        student =  daoStudent.saveStudent("John", "Snow", "Winter@mail.ru", 31);
    }
    @AfterAll
    static void deleteStudent(){
        daoStudent.deleteStudent("Winter@mail.ru");
    }

    @Test
    void addStudentToGradebookTest() {
        int result = daoGradebook.addStudentToGradebook(student.getId(), cursId);
        daoGradebook.deleteStudentFromGradebook(student.getId(), cursId);
        assertEquals(student.getId(), result);
    }

    @Test
    void addGradeTest() {
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        int result = daoGradebook.addGrade(student.getId(), cursId);
        daoGradebook.deleteStudentFromGradebook(student.getId(), cursId);
        assertEquals(student.getId(), result);
    }

    @Test
    void getGradeTest() {
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        daoGradebook.addGrade(student.getId(), cursId);
        int result = daoGradebook.getGrade(student.getId(), cursId);
        assertEquals(1, result);
        daoGradebook.deleteStudentFromGradebook(student.getId(), cursId);
    }

    @Test
    void deleteStudentFromGradebookTest(){
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        daoGradebook.deleteStudentFromGradebook(student.getId(), cursId);
        assertFalse(daoGradebook.isExist(student.getId()));
    }

    @Test
    void isExistTest(){
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        assertTrue(daoGradebook.isExist(student.getId()));
        daoGradebook.deleteStudentFromGradebook(student.getId(), cursId);
    }
}
