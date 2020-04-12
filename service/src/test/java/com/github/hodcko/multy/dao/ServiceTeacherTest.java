package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.service.IServiceTeacher;
import com.github.hodcko.multy.service.impl.ServiceTeacherManager;
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
    private static IDaoTeacher iDaoTeacher;


    @InjectMocks
    private static IServiceTeacher iServiceTeacher;


    @BeforeAll
    public static void createInstance() {
        iServiceTeacher = ServiceTeacherManager.getInstance();
    }

    @Test
    void saveStudentTest(){
        Teacher teacher = new Teacher(1, "John", "Snow", "Winter@gmail.com",  1);
        when(iDaoTeacher.saveTeacher("John", "Snow", "Winter@gmail.com", 1)).thenReturn(teacher);
        Teacher teacherTest = iServiceTeacher.saveTeacher("John", "Snow", "Winter@gmail.com",  1);
        assertEquals(teacher, teacherTest);
    }

    @Test
    void getStudentTest(){
        Teacher teacher = new Teacher(1, "John", "Snow", "Winter@gmail.com",  1);
        when(iDaoTeacher.getTeacher(1)).thenReturn(teacher);
        Teacher teacherTest = iServiceTeacher.getTeacher( 1);
        assertEquals(teacher, teacherTest);
    }


}

