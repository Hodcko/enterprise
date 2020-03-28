
package com.hodcko;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
public class StudentTest {
    Student studentVasya = new Student("Vasya", "Pupkin");
    Student studentJack = new Student("Jack", "Daniels");
    Student studentHello = new Student("Hello", "World");


    @BeforeEach
    public  void setSpec(){
        studentVasya.setSpec("Java");
        studentJack.setSpec("PHP");
        studentHello.setSpec("Assembler");
    }


    @Test
    @DisplayName("getName")
    public void getNameTest(){
        assertEquals("Vasya", studentVasya.getName());
        assertEquals("Jack", studentJack.getName());
        assertEquals("Hello", studentHello.getName());
    }

    @Test
    @DisplayName("getSecondName")
    public void getSecondNameTest(){
        assertEquals("Pupkin", studentVasya.getSecondName());
        assertEquals("Daniels", studentJack.getSecondName());
        assertEquals("World", studentHello.getSecondName());
    }

    @Test
    @DisplayName("getSpec")
    public void getSpecTest(){
        assertEquals("Java", studentVasya.getSpec());
        assertEquals("PHP", studentJack.getSpec());
        assertEquals("Assembler", studentHello.getSpec());
    }
}

