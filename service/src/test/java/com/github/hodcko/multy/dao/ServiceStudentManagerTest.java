package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.service.IServiceStudent;
import com.github.hodcko.multy.service.impl.ServiceStudentManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceStudentManagerTest {

    @Mock
    private static IDaoStudent iDaoStudent;


    @InjectMocks
    private static IServiceStudent iServiceStudent;


    @BeforeAll
    public static void createInstance() {
        iServiceStudent = ServiceStudentManager.getInstance();
    }

    @Test
    void saveStudentTest(){
        Student student = new Student(1, "John", "Snow", "Winter@gmail.com", 30, 1);
        when(iDaoStudent.saveStudent("John", "Snow", "Winter@gmail.com", 30, 1)).thenReturn(student);
        Student studentTest = iServiceStudent.saveStudent("John", "Snow", "Winter@gmail.com", 30, 1);
        assertEquals(student, studentTest);
    }

    @Test
    void getStudentTest(){
        Student student = new Student(1, "John", "Snow", "Winter@gmail.com", 30, 1);
        when(iDaoStudent.getStudent(1)).thenReturn(student);
        Student studentTest = iServiceStudent.getStudent( 1);
        assertEquals(student, studentTest);
    }


}
