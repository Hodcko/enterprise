package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.dao.utils.AutoIncrementChanger;
import com.github.hodcko.multy.model.Student;
import org.junit.jupiter.api.*;


public class DaoStudentTest {
    DaoStudent daoStudent = DaoStudentDefault.getInstance();
    @BeforeEach
     public void change(){
        AutoIncrementChanger.changeAutoIncrement("student");
    }
    @AfterEach
    public void changes(){
        AutoIncrementChanger.changeAutoIncrement("student");
    }


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
}
