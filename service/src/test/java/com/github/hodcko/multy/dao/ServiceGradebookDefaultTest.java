package com.github.hodcko.multy.dao;



import com.github.hodcko.multy.service.impl.ServiceGradebookDefault;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceGradebookDefaultTest {

    @Mock
    private DaoGradebook daoGradebook;

    @InjectMocks
    private ServiceGradebookDefault serviceGradebook;


    final int studentId = 1;
    final int cursIdJava = 1;
    final int cursIdPHP = 2;
    final int cursIdC = 3;


    @Test
    void addStudentToGradebookTest(){
        when(daoGradebook.addStudentToGradebook(studentId, cursIdJava)).thenReturn(studentId);
        int result = serviceGradebook.addStudentToGradebook(studentId, cursIdJava);
        assertEquals(studentId, result);
    }

    @Test
    void addGradeTest(){
        when(daoGradebook.addGrade(studentId, cursIdJava)).thenReturn(studentId);
        int result = serviceGradebook.addGrade(studentId, cursIdJava);
        assertEquals(studentId, result);
    }

    @Test
    void getGradeTest(){
        int grade = 1;
        when(daoGradebook.getGrade(studentId, cursIdJava)).thenReturn(grade);
        int result = serviceGradebook.getGrade(studentId, cursIdJava);
        assertEquals(grade, result);
    }

    @Test
    void isExistTest(){
        when(daoGradebook.isExist(studentId)).thenReturn(true);
        boolean result = serviceGradebook.isExist(studentId);
        assertTrue(result);
    }

    @Test
    void deleteStudentFromGradebookTest(){
        when(daoGradebook.deleteStudentFromGradebook(studentId, cursIdJava)).thenReturn(true);
        boolean result = serviceGradebook.deleteStudentFromGradebook(studentId, cursIdJava);
        assertTrue(result);
    }

    @Test
    void checkJavaTest(){
        serviceGradebook.addStudentToGradebook(studentId, cursIdJava);
        int result =  serviceGradebook.checkTest(studentId, cursIdJava,"java","programming", "interface","4", "8");
        assertEquals(serviceGradebook.getGrade(studentId, cursIdJava), result);
        serviceGradebook.deleteStudentFromGradebook(studentId, cursIdJava);
    }

    @Test
    void checkPHPTest(){
        serviceGradebook.addStudentToGradebook(studentId, cursIdPHP);
        int result =  serviceGradebook.checkTest(studentId, cursIdPHP,"dynamic","4", "$","5", "::");
        assertEquals(serviceGradebook.getGrade(studentId, cursIdPHP), result);
        serviceGradebook.deleteStudentFromGradebook(studentId, cursIdPHP);
    }

    @Test
    void checkCTest(){
        serviceGradebook.addStudentToGradebook(studentId, cursIdC);
        int result =  serviceGradebook.checkTest(studentId, cursIdC,"c","bool", "yes","parametric", "6");
        assertEquals(serviceGradebook.getGrade(studentId, cursIdC), result);
        serviceGradebook.deleteStudentFromGradebook(studentId, cursIdC);
    }
}
