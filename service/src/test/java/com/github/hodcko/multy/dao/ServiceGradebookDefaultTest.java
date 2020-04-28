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
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    final int studentId = 1;


    @Test
    void addStudentToGradebookTest(){
        when(daoGradebook.addStudentToGradebook(studentId)).thenReturn(studentId);
        int result = serviceGradebook.addStudentToGradebook(studentId);
        assertEquals(studentId, result);
    }

    @Test
    void addGradeTest(){
        when(daoGradebook.addGrade(studentId)).thenReturn(studentId);
        int result = serviceGradebook.addGrade(studentId);
        assertEquals(studentId, result);
    }

    @Test
    void getGradeTest(){
        int grade = 1;
        when(daoGradebook.getGrade(studentId)).thenReturn(grade);
        int result = serviceGradebook.getGrade(studentId);
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
    void checkTestTest(){
        serviceGradebook.addStudentToGradebook(studentId);
        int result =  serviceGradebook.checkTest(studentId, "java","programming", "interface","4", "8");
        assertEquals(serviceGradebook.getGrade(studentId), result);
    }
}
