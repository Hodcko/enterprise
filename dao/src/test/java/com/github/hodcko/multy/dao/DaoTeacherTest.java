package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.config.DaoConfig;

import com.github.hodcko.multy.dao.converter.TeacherConverter;
import com.github.hodcko.multy.dao.entity.TeacherEntity;
import com.github.hodcko.multy.model.Teacher;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    Teacher teacherTest;
    private final TeacherEntity teacherEntity = new TeacherEntity(1, "John", "Snow", "winter@mail.com", 1);


    @BeforeEach
    void saver(){
        teacherTest = daoTeacher.saveTeacher("John", "Snow", "winter@gmail.com", 1);
    }


    @Test
    void saveTeacherTest() {
        Teacher teacher = daoTeacher.getTeacher(teacherTest.getId());
        Assertions.assertEquals(teacher, teacherTest);
    }

    @Test
    void getTeacherTest() {
        Assertions.assertEquals(daoTeacher.getTeacher(teacherTest.getId()), teacherTest);
    }

    @Test
    void deleteTeacherTest() {
        daoTeacher.deleteTeacher(teacherTest.getEmail());
        Assertions.assertNull(daoTeacher.getTeacher(teacherTest.getId()));
    }

    @Test
    void isExistTest() {
        boolean teacherResult =  daoTeacher.isExist(teacherTest.getEmail());
        assertTrue(teacherResult);
    }

    @Test
    void DaoGetIdByEmailTest() {
        int teacherId = daoTeacher.getId(teacherTest.getEmail());
        assertEquals(teacherTest.getId(), teacherId);
    }

    @Test
    void passwordGenerateTest(){
        Assertions.assertEquals(daoTeacher.passwordGenerate(teacherTest.getEmail()), teacherTest.getSecondName()+teacherTest.getId());
    }

    @Test
    void teacherConverterToEntityTest(){
        TeacherEntity teacherEntity = TeacherConverter.toEntity(teacherTest);
        Teacher teacher1 = TeacherConverter.fromEntity(teacherEntity);
        assertNotNull(teacher1);
        assertNotNull(teacherEntity);
    }

    @Test
    void teacherConverterFromEntityTest(){
        Teacher teacher = TeacherConverter.fromEntity(teacherEntity);
        assertNotNull(teacher);
    }
}
