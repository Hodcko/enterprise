package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoStudentManager;
import com.github.hodcko.multy.dao.utils.AutoIncrementChanger;
import com.github.hodcko.multy.model.Student;
import org.junit.jupiter.api.*;


public class DaoStudentTest {
    IDaoStudent iDaoStudent = DaoStudentManager.getInstance();
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
        Student student = iDaoStudent.saveStudent("John", "Snow", "winter@gmail.com", 30, 1);
        Student studentTest = iDaoStudent.getStudent(student.getId());
        iDaoStudent.deleteStudent("winter@gmail.com");
        Assertions.assertEquals(student, studentTest);

    }
}
