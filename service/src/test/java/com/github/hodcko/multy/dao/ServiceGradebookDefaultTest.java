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


    private final int studentId = 1;
    private final int cursIdJava = 1;
    private final int cursIdPHP = 2;
    private final int cursIdC = 3;
    private final String java1 = "java";
    private final String java2 = "programming";
    private final String java3 = "interface";
    private final String java4 = "4";
    private final String java5 = "8";
    private final String php1 = "dynamic";
    private final String php2 = "4";
    private final String php3 = "$";
    private final String php4 = "5";
    private final String php5 = "::";
    private final String c1 = "c";
    private final String c2 = "bool";
    private final String c3 = "yes";
    private final String c4 = "parametric";
    private final String c5 = "6";


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
        when(daoGradebook.deleteStudentFromGradebook(studentId)).thenReturn(true);
        boolean result = serviceGradebook.deleteStudentFromGradebook(studentId);
        assertTrue(result);
    }

    @Test
    void checkJavaTest(){
        serviceGradebook.addStudentToGradebook(studentId, cursIdJava);
        int result =  serviceGradebook.checkTest(studentId, cursIdJava, java1, java2, java3, java4, java5);
        assertEquals(serviceGradebook.getGrade(studentId, cursIdJava), result);
        serviceGradebook.deleteStudentFromGradebook(studentId);
    }

    @Test
    void checkPHPTest(){
        serviceGradebook.addStudentToGradebook(studentId, cursIdPHP);
        int result =  serviceGradebook.checkTest(studentId, cursIdPHP, php1, php2, php3, php4, php5);
        assertEquals(serviceGradebook.getGrade(studentId, cursIdPHP), result);
        serviceGradebook.deleteStudentFromGradebook(studentId);
    }

    @Test
    void checkCTest(){
        serviceGradebook.addStudentToGradebook(studentId, cursIdC);
        int result =  serviceGradebook.checkTest(studentId, cursIdC, c1, c2, c3, c4, c5);
        assertEquals(serviceGradebook.getGrade(studentId, cursIdC), result);
        serviceGradebook.deleteStudentFromGradebook(studentId);
    }
}
