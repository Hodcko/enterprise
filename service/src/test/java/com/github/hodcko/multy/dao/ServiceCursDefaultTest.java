package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.DTOGroup;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.service.impl.ServiceCursDefault;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceCursDefaultTest {

    @Mock
    private static DaoCurs daoCurs;

    @InjectMocks
    private static ServiceCursDefault iServiceCursDefault;


    @Test
    void createCursTest(){
        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10,11), LocalDate.of(2020, 10,11));
        when(daoCurs.createCurs(curs.getName(), curs.getStart(), curs.getEnd())).thenReturn(curs);
        Curs cursTest = iServiceCursDefault.createCurs("Java", LocalDate.of(2020, 10,11), LocalDate.of(2020, 10,11));
        assertEquals(curs, cursTest);
    }

    @Test
    void getCursIdTest(){
        int cursIdJava = iServiceCursDefault.getCurs_id("java");
        int cursIdPHP = iServiceCursDefault.getCurs_id("php");
        int cursIdC = iServiceCursDefault.getCurs_id("c++");
        assertEquals(cursIdJava, 1);
        assertEquals(cursIdPHP, 2);
        assertEquals(cursIdC, 3);
    }

    @Test
    void getCursTest(){
        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10,11), LocalDate.of(2020, 10,11));
        when(daoCurs.getCurs(1)).thenReturn(curs);
        Curs cursTest = iServiceCursDefault.getCurs(1);
        assertEquals(curs, cursTest);
    }

    @Test
    void getMyStudentsTest(){
        List<DTOGroup> list = new ArrayList<>();
        DTOGroup dtoGroup = new DTOGroup("Jonh", "Snow", "snow@gmail.com", 5);
        list.add(dtoGroup);
        when(daoCurs.getMyStudents(1, 1)).thenReturn(list);
        List<DTOGroup> dtoGroupList = iServiceCursDefault.getMyStudents(1, 1);
        assertEquals(list, dtoGroupList);
    }

    @Test
    void countOfStudentsTest(){
        List<DTOGroup> list = new ArrayList<>();
        DTOGroup dtoGroup = new DTOGroup("Jonh", "Snow", "snow@gmail.com", 5);
        list.add(dtoGroup);
        when(daoCurs.countOfStudents(1)).thenReturn(list.size());
        int count = iServiceCursDefault.countOfStudents(1);
        assertEquals(list.size(), count);
    }

    @Test
    void getClassmatesTest(){
        List<Student> list = new ArrayList<>();
        Student student = new Student("John", "Snow", "Winter@gmail.com", 30, 1);
        list.add(student);
        when(daoCurs.getClassmates(1)).thenReturn(list);
        List<Student> listStudents = iServiceCursDefault.getClassmates(1);
        assertEquals(list, listStudents);
    }

    @Test
    void deleteCursTest(){
        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10,11), LocalDate.of(2020, 10,11));
        when(daoCurs.deleteCurs(curs.getId())).thenReturn(true);
        boolean result = iServiceCursDefault.deleteCurs(curs.getId());
        assertTrue(result);
    }
}
