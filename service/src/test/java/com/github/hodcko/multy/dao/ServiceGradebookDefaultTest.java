package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.service.ServiceGradebook;
import com.github.hodcko.multy.service.impl.ServiceGradebookDefault;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceGradebookDefaultTest {

    @Mock
    private static DaoGradebook daoGradebook;

    @InjectMocks
    private static ServiceGradebook serviceGradebook;



    @BeforeAll
    public static void createInstance() {
        serviceGradebook = ServiceGradebookDefault.getInstance();
    }


    @Test
    void addStudentToGradebookTest(){
        int studentId = 1;
        when(daoGradebook.addStudentToGradebook(studentId)).thenReturn(studentId);
        int result = serviceGradebook.addStudentToGradebook(studentId);
        assertEquals(studentId, result);
    }

    @Test
    void addGradeTest(){
        int studentId = 1;
        when(daoGradebook.addGrade(studentId)).thenReturn(studentId);
        int result = serviceGradebook.addGrade(studentId);
        assertEquals(studentId, result);
    }

    @Test
    void getGradeTest(){
        int studentId = 1;
        int grade = 1;
        when(daoGradebook.getGrade(studentId)).thenReturn(grade);
        int result = serviceGradebook.getGrade(studentId);
        assertEquals(grade, result);
    }







}
