package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.service.IServiceGradebook;
import com.github.hodcko.multy.service.impl.ServiceGradebook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceGradebookTest {

    @Mock
    private static IDaoGradebook iDaoGradebook;

    @InjectMocks
    private static IServiceGradebook iServiceGradebook;



    @BeforeAll
    public static void createInstance() {
        iServiceGradebook = ServiceGradebook.getInstance();
    }


    @Test
    void addStudentToGradebookTest(){
        int studentId = 1;
        when(iDaoGradebook.addStudentToGradebook(studentId)).thenReturn(studentId);
        int result = iServiceGradebook.addStudentToGradebook(studentId);
        assertEquals(studentId, result);
    }

    @Test
    void addGradeTest(){
        int studentId = 1;
        when(iDaoGradebook.addGrade(studentId)).thenReturn(studentId);
        int result = iServiceGradebook.addGrade(studentId);
        assertEquals(studentId, result);
    }

    @Test
    void getGradeTest(){
        int studentId = 1;
        int grade = 1;
        when(iDaoGradebook.getGrade(studentId)).thenReturn(grade);
        int result = iServiceGradebook.getGrade(studentId);
        assertEquals(grade, result);
    }







}
