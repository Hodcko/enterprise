package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.impl.DaoTeacherManager;

import com.github.hodcko.multy.dao.utils.AutoIncrementChanger;
import com.github.hodcko.multy.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DaoTeacherTest {
    IDaoTeacher iDaoTeacher = DaoTeacherManager.getInstance();
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
        Teacher teacher = new Teacher(3, "John", "Snow", "winter@gmail.com", 1);
        Teacher teacherTest = iDaoTeacher.saveTeacher("John", "Snow", "winter@gmail.com", 1);
        iDaoTeacher.deleteTeacher("winter@gmail.com");
        Assertions.assertEquals(teacher, teacherTest);

    }
}
