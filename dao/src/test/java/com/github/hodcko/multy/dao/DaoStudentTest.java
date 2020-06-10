package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.config.DaoConfig;
import com.github.hodcko.multy.dao.converter.StudentConverter;
import com.github.hodcko.multy.dao.entity.StudentEntity;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DaoStudentTest {

    @Autowired
    private DaoStudent daoStudent;
    @Autowired
    SessionFactory sessionFactory;


    @Test
    void saveStudentTest() {
        Student student = daoStudent.saveStudent("John", "Snow", "winter@gmail.com", 30);
        Student studentTest = daoStudent.getStudent(student.getId());
        daoStudent.deleteStudent("winter@gmail.com");
        Assertions.assertEquals(student, studentTest);
    }

    @Test
    void getStudentTest(){
        Student student = daoStudent.saveStudent("John", "Snow", "winter@gmail.com", 30);
        Assertions.assertEquals(student, daoStudent.getStudent(student.getId()));
        daoStudent.deleteStudent("winter@gmail.com");
    }

    @Test
    void deleteStudentTest(){
        Student student = daoStudent.saveStudent("John", "Snow", "winter@gmail.com", 30);
        Student student1 = daoStudent.getStudent(student.getId());
        daoStudent.deleteStudent(student1.getEmail());
        Assertions.assertNull(daoStudent.getStudent(student1.getId()));
    }

    @Test
    void isExistTest() {
        daoStudent.saveStudent("test", "test", "hodckoq@mail.com", 30);
        boolean studentResult = daoStudent.isExist("hodckoq@mail.com", UserType.STUDENT);
        daoStudent.deleteStudent("hodckoq@mail.com");
        assertTrue(studentResult);
    }

    @Test
    void DaoGetIdByEmailTest() {
        Student student = daoStudent.saveStudent("test", "test", "hodckoq@mail.com", 30);
        int studentId = daoStudent.getId("hodckoq@mail.com", UserType.STUDENT);
        daoStudent.deleteStudent("hodckoq@mail.com");
        assertEquals(student.getId(), studentId);
    }

    @Test
    void passwordGenerateTest(){
        Student student = daoStudent.saveStudent("Jonh", "Snow", "snow@gmail.com", 31);
        Assertions.assertEquals(daoStudent.passwordGenerate(student.getEmail(), UserType.STUDENT), student.getSecondName()+student.getId());
        daoStudent.deleteStudent(student.getEmail());
    }

    @Test
    void studentConvertorTest(){
        Student student = daoStudent.saveStudent("John", "Snow", "winter@gmail.com", 30);
        StudentEntity studentEntity = StudentConverter.toEntity(student);
        Student student1 = StudentConverter.fromEntity(studentEntity);
        assertNotNull(student1);
        assertNotNull(studentEntity);
    }
}
