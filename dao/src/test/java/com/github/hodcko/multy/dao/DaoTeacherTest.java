package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;

import com.github.hodcko.multy.dao.utils.AutoIncrementChanger;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoTeacherTest {
    DaoTeacher daoTeacher = DaoTeacherDefault.getInstance();
    @BeforeEach
    public void change(){
        AutoIncrementChanger.changeAutoIncrement("teacher");
    }
    @AfterEach
    public void changes(){
        AutoIncrementChanger.changeAutoIncrement("teacher");
    }


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
}
