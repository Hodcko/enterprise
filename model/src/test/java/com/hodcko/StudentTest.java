//package com.hodcko;
//
//import org.junit.jupiter.api.*;
//
//import java.util.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class StudentTest {
//    Student studentJohn = new Student("John");
//    Student studentJack = new Student("Jack");
//    Student studentMike = new Student("Mike");
//    List<Integer> list = Arrays.asList(9, 3, 6, 7, 1);
//    List<Integer> list2 = Arrays.asList(4, 8, 1, 4, 10);
//    List<Integer> list3 = Arrays.asList(4, 1, 3, 9, 6);
//    Map<Integer, List<Integer>> map = new HashMap<>();
//
//
//
//    @BeforeEach
//    public void addGrade(){
//        studentJohn.addGrade(9, 3, 6, 7, 1);
//        studentJack.addGrade(4, 8, 1, 4, 10);
//        studentMike.addGrade(4, 1, 3, 9, 6);
//    }
//
//    @AfterEach
//    public void clearGrade(){
//        studentJohn.getGradeList().clear();
//        studentJack.getGradeList().clear();
//        studentMike.getGradeList().clear();
//        Student.getJournal().clear();
//    }
//
//    @Test
//    @DisplayName("addGrade")
//    public void addGradeTest() {
//        Object obj = new Object();
//        Assertions.assertThrows(ClassCastException.class, () -> studentMike.addGrade((Integer)obj));
//    }
//
//    @Test
//    @DisplayName("getAverageGrade")
//    public void getAverageGradeTest(){
//        assertEquals(5.2, studentJohn.getAverageGrade());
//        assertEquals(5.4, studentJack.getAverageGrade());
//        assertEquals(4.6, studentMike.getAverageGrade());
//    }
//
//    @Test
//    @DisplayName("getMaxGrade")
//    public void getMaxGradeTest(){
//        assertEquals(9, studentJohn.getMaxGrade());
//        assertEquals(10, studentJack.getMaxGrade());
//        assertEquals(9, studentMike.getMaxGrade());
//    }
//
//    @Test
//    @DisplayName("getGradeList")
//    public void getGradeListTest(){
//        assertEquals(list, studentJohn.getGradeList());
//        assertEquals(list2, studentJack.getGradeList());
//        assertEquals(list3, studentMike.getGradeList());
//    }
//
//    @Test
//    @DisplayName("getJournal")
//    public void getJournalList(){
//        map.put(studentJohn.getId(), list);
//        map.put(studentJack.getId(), list2);
//        map.put(studentMike.getId(), list3);
//        assertEquals(map, Student.getJournal());
//    }
//
//    @Test
//    @DisplayName("getName")
//    public void getNameTest(){
//        assertEquals("John", studentJohn.getName());
//        assertEquals("Jack", studentJack.getName());
//        assertEquals("Mike", studentMike.getName());
//    }
//
//
//
//
//
//
//
//}
