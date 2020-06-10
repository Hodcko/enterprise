package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.entity.GroupDTO;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
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
    private DaoCurs daoCurs;

    @InjectMocks
    private ServiceCursDefault iServiceCursDefault;

    final Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10,11), LocalDate.of(2020, 10,11));
    final Student student = new Student(1, "John", "Snow", "Winter@gmail.com", 30);
    final Teacher teacher = new Teacher(1, "John", "Snow", "Winter@gmail1.com", 1);
    final GroupDTO groupDTO = new GroupDTO("Jonh", "Snow", "snow@gmail.com", 5);



    @Test
    void createCursTest(){
        when(daoCurs.createCurs(curs.getName(), curs.getStart(), curs.getEnd())).thenReturn(curs);
        Curs cursTest = iServiceCursDefault.createCurs("Java", LocalDate.of(2020, 10,11), LocalDate.of(2020, 10,11));
        assertEquals(curs, cursTest);
    }

    @Test
    void getCursIdTest(){
        int cursIdJava = iServiceCursDefault.getCursId("java");
        int cursIdPHP = iServiceCursDefault.getCursId("php");
        int cursIdC = iServiceCursDefault.getCursId("c++");
        assertEquals(cursIdJava, 1);
        assertEquals(cursIdPHP, 2);
        assertEquals(cursIdC, 3);
    }

    @Test
    void getCursTest(){
        when(daoCurs.getCurs(1)).thenReturn(curs);
        Curs cursTest = iServiceCursDefault.getCurs(curs.getId());
        assertEquals(curs, cursTest);
    }

    @Test
    void getMyStudentsTest(){
        List<GroupDTO> list = new ArrayList<>();
        list.add(groupDTO);
        when(daoCurs.getMyStudents(curs.getId(), 1)).thenReturn(list);
        List<GroupDTO> groupDTOList = iServiceCursDefault.getMyStudents(1, 1);
        assertEquals(list, groupDTOList);
    }

    @Test
    void countOfStudentsTest(){
        List<GroupDTO> list = new ArrayList<>();
        list.add(groupDTO);
        when(daoCurs.countOfStudents(curs.getId())).thenReturn(list.size());
        int count = iServiceCursDefault.countOfStudents(curs.getId());
        assertEquals(list.size(), count);
    }

    @Test
    void getClassmatesTest(){
        List<Student> list = new ArrayList<>();
        list.add(student);
        when(daoCurs.getClassmates(1)).thenReturn(list);
        List<Student> listStudents = iServiceCursDefault.getClassmates(curs.getId());
        assertEquals(list, listStudents);
    }

    @Test
    void getColleaguesTest(){
        List<Teacher> list = new ArrayList<>();
        list.add(teacher);
        when(daoCurs.getColleagues(teacher.getCursId())).thenReturn(list);
        List<Teacher> listTeachers = iServiceCursDefault.getColleagues(curs.getId());
        assertEquals(list, listTeachers);
    }

    @Test
    void deleteCursTest(){
        when(daoCurs.deleteCurs(curs.getId())).thenReturn(true);
        boolean result = iServiceCursDefault.deleteCurs(curs.getId());
        assertTrue(result);
    }

    @Test
    void inviteStudentOnCursTest(){
        when(daoCurs.inviteStudentOnCurs(student.getId(), curs.getId())).thenReturn(true);
        boolean result = iServiceCursDefault.inviteStudentOnCurs(student.getId(), curs.getId());
        assertTrue(result);
    }


}
