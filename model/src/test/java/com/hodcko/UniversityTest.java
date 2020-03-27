//package com.hodcko;
//
//import org.junit.jupiter.api.*;
//
//
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class UniversityTest {
//    Student studentJohn = new Student("John");
//    Student studentJack = new Student("Jack");
//    Student studentMike = new Student("Mike");
//
//    University university = new University();
//    static List<Student> successfulStudent = new LinkedList<>();
//    List<Student> studentsList = new LinkedList<>();
//
//    @BeforeEach
//    public void addStudentToList(){
//        University.addStudentToList(studentJack);
//        University.addStudentToList(studentJohn);
//        University.addStudentToList(studentMike);
//        studentJohn.addGrade(9, 9, 9, 7, 8);
//        studentJack.addGrade(4, 8, 1, 4, 10);
//        studentMike.addGrade(4, 1, 3, 9, 6);
//    }
//
//    @AfterEach
//    public void removeStudentsFromlist(){
//        studentJohn.getGradeList().clear();
//        studentJack.getGradeList().clear();
//        studentMike.getGradeList().clear();
//        University.getStudentsList().clear();
//    }
//
//    @Test
//    @DisplayName("CountOfStudents")
//    public void countOfStudentsTest(){
//        assertEquals(3, university.countOfStudents());
//    }
//
//    @Test
//    @DisplayName("getSuccessfulStudent")
//    public void getSuccessfulStudentTest(){
//        successfulStudent.add(studentJohn);
//        assertEquals(successfulStudent, university.getSuccessfulStudent());
//    }
//
//    @Test
//    @DisplayName("getStudentList")
//    public void getStudentListTest(){
//        studentsList.add(studentJack);
//        studentsList.add(studentJohn);
//        studentsList.add(studentMike);
//        assertEquals(studentsList, University.getStudentsList());
//    }
//
//
//
//
//
//}
