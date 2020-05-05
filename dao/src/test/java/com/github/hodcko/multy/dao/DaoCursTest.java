package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.GroupDTO;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DaoCursTest {

    final DaoCurs daoCurs = DaoCursDefault.getInstance();
    final DaoStudent daoStudent = DaoStudentDefault.getInstance();
    final DaoTeacher daoTeacher = DaoTeacherDefault.getInstance();
    final DaoGradebook daoGradebook = DaoGradebookDefault.getInstance();
    final Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));



    @Test
    void createCursTest() {
        Curs testCurs = daoCurs.createCurs("Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        daoCurs.deleteCurs(testCurs.getId());
        assertEquals(curs, testCurs);
    }

    @Test
    void getCursTest() {
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
        Student student = daoStudent.saveStudent("Jogn", "Snow", "snow@gmail.com", 31);
        daoGradebook.addStudentToGradebook(student.getId(), curs.getId());
        daoGradebook.addGrade(student.getId(), curs.getId());
        daoCurs.inviteStudentOnCurs(student.getId(), curs.getId());
        GroupDTO groupDTO = new GroupDTO(student.getName(), student.getSecondName(), student.getEmail(), daoGradebook.getGrade(student.getId(), curs.getId()));
        List<GroupDTO> list = new ArrayList<>();
        list.add(groupDTO);
        assertEquals(daoCurs.getMyStudents(curs.getId(),1), list);
        daoStudent.deleteStudent(student.getEmail());
        daoGradebook.deleteStudentFromGradebook(student.getId(), curs.getId());
    }

    @Test
    void countOfStudentsTest(){
        Student student = daoStudent.saveStudent("Jogn", "Snow", "snow@gmail.com", 31);
        daoGradebook.addStudentToGradebook(student.getId(), curs.getId());
        daoGradebook.addGrade(student.getId(), curs.getId());
        daoCurs.inviteStudentOnCurs(student.getId(), curs.getId());
        GroupDTO groupDTO = new GroupDTO(student.getName(), student.getSecondName(), student.getEmail(), daoGradebook.getGrade(student.getId(), curs.getId()));
        List<GroupDTO> list = new ArrayList<>();
        list.add(groupDTO);
        assertEquals(daoCurs.countOfStudents(curs.getId()), 1);
        daoStudent.deleteStudent(student.getEmail());
        daoGradebook.deleteStudentFromGradebook(student.getId(), curs.getId());
    }

    @Test
    void getClassmatesTest(){
        Student student = daoStudent.saveStudent("Jogn", "Snow", "snow@gmail.com", 31);
        Student student1 = daoStudent.saveStudent("Jogn", "Snow", "snow@qgmail.com", 31);
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        daoCurs.inviteStudentOnCurs(student.getId(), curs.getId());
        daoCurs.inviteStudentOnCurs(student1.getId(), curs.getId());
        daoGradebook.addStudentToGradebook(student.getId(), curs.getId());
        daoGradebook.addStudentToGradebook(student1.getId(), curs.getId());
        assertArrayEquals(daoCurs.getClassmates(curs.getId()).toArray(), list.toArray());
        daoStudent.deleteStudent(student.getEmail());
        daoStudent.deleteStudent(student1.getEmail());
        daoGradebook.deleteStudentFromGradebook(student.getId(), curs.getId());
        daoGradebook.deleteStudentFromGradebook(student1.getId(), curs.getId());
    }

    @Test
    void getColleaguesTest(){
        Teacher teacher =  daoTeacher.saveTeacher("John", "Snow", "Winter@gmail.com", 1);
        Teacher teacher1 =  daoTeacher.saveTeacher("John", "Snoww", "Wwinter@gmail.com", 1);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        teachers.add(teacher1);
        assertArrayEquals(daoCurs.getColleagues(teacher.getCursId()).toArray(), teachers.toArray());
        daoTeacher.deleteTeacher(teacher.getEmail());
        daoTeacher.deleteTeacher(teacher1.getEmail());
    }




}
