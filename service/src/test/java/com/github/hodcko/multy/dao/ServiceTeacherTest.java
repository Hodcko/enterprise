package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.service.impl.ServiceTeacherDefault;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTeacherTest {

    @Mock
    private DaoTeacher daoTeacher;

    @InjectMocks
    private ServiceTeacherDefault serviceTeacher;

    private final  Teacher teacher = new Teacher(1, "John", "Snow", "Winter@gmail.com",  1);
    private final boolean testResult = true;


    @Test
    void saveTeacherTest(){
        when(daoTeacher.saveTeacher(teacher.getName(), teacher.getSecondName(), teacher.getEmail(), teacher.getCursId())).thenReturn(teacher);
        Teacher teacherTest = serviceTeacher.saveTeacher(teacher.getName(), teacher.getSecondName(), teacher.getEmail(), teacher.getCursId());
        assertEquals(teacher, teacherTest);
    }

    @Test
    void getTeacherTest(){
        when(daoTeacher.getTeacher(teacher.getId())).thenReturn(teacher);
        Teacher teacherTest = serviceTeacher.getTeacher( teacher.getId());
        assertEquals(teacher, teacherTest);
    }

    @Test
    void deleteTeacherTest(){
        when(daoTeacher.deleteTeacher(teacher.getEmail())).thenReturn(testResult);
        boolean result = serviceTeacher.deleteTeacher( teacher.getEmail());
        assertTrue(result);
    }


}

