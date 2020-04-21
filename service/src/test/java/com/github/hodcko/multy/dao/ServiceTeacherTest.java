package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.service.ServiceTeacher;
import com.github.hodcko.multy.service.impl.ServiceTeacherDefault;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTeacherTest {

    @Mock
    private static DaoTeacher daoTeacher;


    @InjectMocks
    private static ServiceTeacher serviceTeacher;


    @BeforeAll
    public static void createInstance() {
        serviceTeacher = ServiceTeacherDefault.getInstance();
    }

    @Test
    void saveStudentTest(){
        Teacher teacher = new Teacher(1, "John", "Snow", "Winter@gmail.com",  1);
        when(daoTeacher.saveTeacher("John", "Snow", "Winter@gmail.com", 1)).thenReturn(teacher);
        Teacher teacherTest = serviceTeacher.saveTeacher("John", "Snow", "Winter@gmail.com",  1);
        assertEquals(teacher, teacherTest);
    }

    @Test
    void getStudentTest(){
        Teacher teacher = new Teacher(1, "John", "Snow", "Winter@gmail.com",  1);
        when(daoTeacher.getTeacher(1)).thenReturn(teacher);
        Teacher teacherTest = serviceTeacher.getTeacher( 1);
        assertEquals(teacher, teacherTest);
    }


}

