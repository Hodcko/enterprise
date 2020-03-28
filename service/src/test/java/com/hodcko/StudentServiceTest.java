package com.hodcko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceTest {
    Service service = StudentService.getInstance();

    Student studentVasya = new Student("Vasya", "Pupkin");
    Student studentJack = new Student("Jack", "Daniels");
    Student studentHello = new Student("Hello", "World");
    List<Student> studentList = new ArrayList<>();


    @BeforeEach
    public void addStidentToList(){
        studentList.add(studentVasya);
        studentList.add(studentJack);
        studentList.add(studentHello);
        service.saveStudent(studentVasya);
        service.saveStudent(studentJack);
        service.saveStudent(studentHello);
        service.setSpec("java", studentVasya);
    }

    @AfterEach
    public void deleteStudentFromList(){
        studentList = null;
    }

    @Test
    @DisplayName("getStudentList")
    public void getStudentListTest(){
        assertEquals(studentList, service.getStudentsList());
    }

    @Test
    @DisplayName("getSpec")
    public void getSpecTest(){
        assertEquals("java", service.getSpec(studentVasya));
    }

}
