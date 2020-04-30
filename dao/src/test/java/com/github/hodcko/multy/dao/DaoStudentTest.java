package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.UserType;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DaoStudentTest {
    DaoStudent daoStudent = DaoStudentDefault.getInstance();

    @Test
    void saveStudentTest() {
        Student student = daoStudent.saveStudent("John", "Snow", "winter@gmail.com", 30, 1);
        Student studentTest = daoStudent.getStudent(student.getId());
        daoStudent.deleteStudent("winter@gmail.com");
        Assertions.assertEquals(student, studentTest);
    }

    @Test
    void getStudentTest(){
        Student student = daoStudent.saveStudent("John", "Snow", "winter@gmail.com", 30, 1);
        Assertions.assertEquals(student, daoStudent.getStudent(student.getId()));
        daoStudent.deleteStudent("winter@gmail.com");
    }

    @Test
    void deleteStudentTest(){
        Student student = daoStudent.saveStudent("John", "Snow", "winter@gmail.com", 30, 1);
        daoStudent.deleteStudent("winter@gmail.com");
        Assertions.assertNull(daoStudent.getStudent(student.getId()));
    }

    @Test
    void isExistTest() {
        daoStudent.saveStudent("test", "test", "hodckoq@mail.com", 30, 1);
        boolean studentResult = daoStudent.isExist("hodckoq@mail.com", UserType.STUDENT);
        daoStudent.deleteStudent("hodckoq@mail.com");
        assertTrue(studentResult);
    }

    @Test
    void DaoGetIdByEmailTest() {
        Student student = daoStudent.saveStudent("test", "test", "hodckoq@mail.com", 30, 1);
        int studentId = daoStudent.getId("hodckoq@mail.com", UserType.STUDENT);
        daoStudent.deleteStudent("hodckoq@mail.com");
        assertEquals(student.getId(), studentId);
    }

    @Test
    void passwordGenerateTest(){
        Student student = daoStudent.saveStudent("Jonh", "Snow", "snow@gmail.com", 31, 1);
        Assertions.assertEquals(daoStudent.passwordGenerate(student.getEmail(), UserType.STUDENT), student.getSecondName()+student.getId());
        daoStudent.deleteStudent(student.getEmail());
    }
}
