package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.GroupDTO;
import com.github.hodcko.multy.model.Student;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DaoCursTest {

    final DaoCurs daoCurs = DaoCursDefault.getInstance();
    final DaoStudent daoStudent = DaoStudentDefault.getInstance();
    final DaoGradebook daoGradebook = DaoGradebookDefault.getInstance();


    @Test
    void createCursTest() {
        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        Curs testCurs = daoCurs.createCurs("Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        daoCurs.deleteCurs(testCurs.getId());
        assertEquals(curs, testCurs);
    }

    @Test
    void getCursTest() {
        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        Curs testCurs = daoCurs.createCurs("Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        assertEquals(curs, daoCurs.getCurs(curs.getId()));
        daoCurs.deleteCurs(testCurs.getId());
    }

    @Test
    void deleteCurs(){
        Curs testCurs = daoCurs.createCurs("Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        daoCurs.deleteCurs(testCurs.getId());
        assertNull(daoCurs.getCurs(testCurs.getId()).getEnd());
    }

    @Test
    void getMyStudentsTest(){
        Student student = daoStudent.saveStudent("Jogn", "Snow", "snow@gmail.com", 31, 1);
        daoGradebook.addStudentToGradebook(student.getId());
        daoGradebook.addGrade(student.getId());
        GroupDTO groupDTO = new GroupDTO(student.getName(), student.getSecondName(), student.getEmail(), daoGradebook.getGrade(student.getId()));
        List<GroupDTO> list = new ArrayList<>();
        list.add(groupDTO);
        assertEquals(daoCurs.getMyStudents(student.getCurs_id(),1), list);
        daoStudent.deleteStudent(student.getEmail());
        daoGradebook.deleteStudentFromGradebook(student.getId());
    }

    @Test
    void countOfStudentsTest(){
        Student student = daoStudent.saveStudent("Jogn", "Snow", "snow@gmail.com", 31, 1);
        daoGradebook.addStudentToGradebook(student.getId());
        daoGradebook.addGrade(student.getId());
        GroupDTO groupDTO = new GroupDTO(student.getName(), student.getSecondName(), student.getEmail(), daoGradebook.getGrade(student.getId()));
        List<GroupDTO> list = new ArrayList<>();
        list.add(groupDTO);
        assertEquals(daoCurs.countOfStudents(student.getCurs_id()), 1);
        daoStudent.deleteStudent(student.getEmail());
        daoGradebook.deleteStudentFromGradebook(student.getId());
    }

    @Test
    void getClassmates(){
        Student student = daoStudent.saveStudent("Jogn", "Snow", "snow@gmail.com", 31, 1);
        Student student1 = daoStudent.saveStudent("Jogn", "Snow", "snow@qgmail.com", 31, 1);
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        assertArrayEquals(daoCurs.getClassmates(student.getCurs_id()).toArray(), list.toArray());
        daoStudent.deleteStudent(student.getEmail());
        daoStudent.deleteStudent(student1.getEmail());
    }




}
