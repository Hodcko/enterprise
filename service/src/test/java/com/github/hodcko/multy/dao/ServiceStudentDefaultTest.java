package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.service.impl.ServiceStudentDefault;
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
    DaoStudent daoStudent;

    @InjectMocks
    ServiceStudentDefault serviceStudent;

    private final Student student = new Student(1, "John", "Snow", "Winter@gmail.com", 30);
    private final boolean testResult = true;

    @Test
    void saveStudentTest(){
        when(daoStudent.saveStudent(student.getName(), student.getSecondName(), student.getEmail(), student.getAge())).thenReturn(student);
        Student studentTest = serviceStudent.saveStudent(student.getName(), student.getSecondName(), student.getEmail(), student.getAge());
        assertEquals(student, studentTest);
    }

    @Test
    void getStudentTest(){
        when(daoStudent.getStudent(student.getId())).thenReturn(student);
        Student studentTest = serviceStudent.getStudent( student.getId());
        assertEquals(student, studentTest);
    }

    @Test
    void deleteStudentTest(){
        when(daoStudent.deleteStudent(student.getEmail())).thenReturn(testResult);
        boolean result = serviceStudent.deleteStudent( student.getEmail());
        assertTrue(result);
    }


}
