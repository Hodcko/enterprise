package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.config.DaoConfig;
import com.github.hodcko.multy.dao.converter.StudentConverter;
import com.github.hodcko.multy.dao.entity.StudentEntity;
import com.github.hodcko.multy.model.Student;
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

    private Student student;
    private final StudentEntity studentEntity = new StudentEntity(1, "John", "Snow", "winter@gmail.com", 30);


    @BeforeEach
    void saver(){
        student = daoStudent.saveStudent("John", "Snow", "winter@gmail.com", 30);
    }


    @Test
    void saveStudentTest() {
        Student studentTest = daoStudent.getStudent(student.getId());
        Assertions.assertEquals(student, studentTest);
    }

    @Test
    void getStudentTest(){
        Assertions.assertEquals(student, daoStudent.getStudent(student.getId()));
    }

    @Test
    void deleteStudentTest(){
        Student student1 = daoStudent.getStudent(student.getId());
        daoStudent.deleteStudent(student1.getEmail());
        Assertions.assertNull(daoStudent.getStudent(student1.getId()));
    }

    @Test
    void isExistTest() {
        boolean studentResult = daoStudent.isExist(student.getEmail());
        assertTrue(studentResult);
    }

    @Test
    void DaoGetIdByEmailTest() {
        int studentId = daoStudent.getId(student.getEmail());
        assertEquals(student.getId(), studentId);
    }

    @Test
    void passwordGenerateTest(){
        Assertions.assertEquals(daoStudent.passwordGenerate(student.getEmail()), student.getSecondName()+student.getId());
    }

    @Test
    void studentConvertorToEntityTest(){
        StudentEntity studentEntity = StudentConverter.toEntity(student);
        assertNotNull(studentEntity);
    }

    @Test
    void studentConvertorFromEntityTest(){
        Student student1 = StudentConverter.fromEntity(studentEntity);
        assertNotNull(student1);
    }
}
