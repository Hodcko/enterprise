package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;

import com.github.hodcko.multy.dao.utils.AutoIncrementChanger;
import com.github.hodcko.multy.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
