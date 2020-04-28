package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.service.ServiceStudent;
import com.github.hodcko.multy.service.impl.ServiceStudentDefault;
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
public class ServiceStudentDefaultTest {

    @Mock
    private static DaoStudent daoStudent;


    @InjectMocks
    private static ServiceStudent serviceStudent;


    @BeforeAll
    public static void createInstance() {
        serviceStudent = ServiceStudentDefault.getInstance();
    }

    @Test
    void saveStudentTest(){
        Student student = new Student(1, "John", "Snow", "Winter@gmail.com", 30, 1);
        when(daoStudent.saveStudent("John", "Snow", "Winter@gmail.com", 30, 1)).thenReturn(student);
        Student studentTest = serviceStudent.saveStudent("John", "Snow", "Winter@gmail.com", 30, 1);
        assertEquals(student, studentTest);
    }

    @Test
    void getStudentTest(){
        Student student = new Student(1, "John", "Snow", "Winter@gmail.com", 30, 1);
        when(daoStudent.getStudent(1)).thenReturn(student);
        Student studentTest = serviceStudent.getStudent( 1);
        assertEquals(student, studentTest);
    }

    @Test
    void deleteStudentTest(){
        Student student = new Student(1, "John", "Snow", "Winter@gmail.com", 30, 1);
        when(daoStudent.deleteStudent(student.getEmail())).thenReturn(true);
        boolean result = serviceStudent.deleteStudent( student.getEmail());
        assertTrue(result);
    }


}
