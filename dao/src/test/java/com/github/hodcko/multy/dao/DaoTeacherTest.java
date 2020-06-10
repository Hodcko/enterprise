package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.config.DaoConfig;

import com.github.hodcko.multy.dao.converter.TeacherConverter;
import com.github.hodcko.multy.dao.entity.TeacherEntity;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DaoTeacherTest {

    @Autowired
    private DaoTeacher daoTeacher;
    @Autowired
    SessionFactory sessionFactory;


    @Test
    void saveTeacherTest() {
        Teacher teacherTest = daoTeacher.saveTeacher("John", "Snow", "winter@gmail.com", 1);
        Teacher teacher = daoTeacher.getTeacher(teacherTest.getId());
        daoTeacher.deleteTeacher("winter@gmail.com");
        Assertions.assertEquals(teacher, teacherTest);
    }

    @Test
    void getTeacherTest() {
        Teacher teacherTest = daoTeacher.saveTeacher("John", "Snow", "winter@gmail.com", 1);
        Assertions.assertEquals(daoTeacher.getTeacher(teacherTest.getId()), teacherTest);
        daoTeacher.deleteTeacher("winter@gmail.com");
    }

    @Test
    void deleteTeacherTest() {
        Teacher teacherTest = daoTeacher.saveTeacher("John", "Snow", "winter@gmail.com", 1);
        daoTeacher.deleteTeacher("winter@gmail.com");
        Assertions.assertNull(daoTeacher.getTeacher(teacherTest.getId()));
    }

    @Test
    void isExistTest() {
        daoTeacher.saveTeacher("test", "test", "hodckoq@mail.com",  1);
        boolean teacherResult =  daoTeacher.isExist("hodckoq@mail.com", UserType.TEACHER);
        daoTeacher.deleteTeacher("hodckoq@mail.com");
        assertTrue(teacherResult);
    }

    @Test
    void DaoGetIdByEmailTest() {
        Teacher teacher = daoTeacher.saveTeacher("test", "test", "hodckoq@mail.com",  1);
        int teacherId = daoTeacher.getId("hodckoq@mail.com", UserType.TEACHER);
        daoTeacher.deleteTeacher("hodckoq@mail.com");
        assertEquals(teacher.getId(), teacherId);
    }

    @Test
    void passwordGenerateTest(){
        Teacher teacher = daoTeacher.saveTeacher("Jonh", "Snow", "snow@gmail.com",  1);
        Assertions.assertEquals(daoTeacher.passwordGenerate(teacher.getEmail(), UserType.TEACHER), teacher.getSecondName()+teacher.getId());
        daoTeacher.deleteTeacher(teacher.getEmail());
    }

    @Test
    void teacherConverterTest(){
        Teacher teacher = daoTeacher.saveTeacher("Jonh", "Snow", "snow@gmail.com",  1);
        TeacherEntity teacherEntity = TeacherConverter.toEntity(teacher);
        Teacher teacher1 = TeacherConverter.fromEntity(teacherEntity);
        assertNotNull(teacher1);
        assertNotNull(teacherEntity);
    }
}
