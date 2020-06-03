package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.config.DaoConfig;

import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.GroupDTO;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional

public class DaoCursTest {

    @Autowired
    private DaoCurs daoCurs;
    @Autowired
    private DaoStudent daoStudent;
    @Autowired
    private DaoTeacher daoTeacher;
    @Autowired
    private DaoGradebook daoGradebook;
    @Autowired
    private SessionFactory sessionFactory;


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
        sessionFactory.getCurrentSession().evict(testCurs);
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
        daoGradebook.deleteStudentFromGradebook(student.getId(), curs.getId());
        daoStudent.deleteStudent(student.getEmail());

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
        daoGradebook.deleteStudentFromGradebook(student.getId(), curs.getId());
        daoStudent.deleteStudent(student.getEmail());

    }

    @Test
    void getClassmatesTest(){

        Student student = daoStudent.saveStudent("Jogn", "Snow", "snow@gmail.com", 31);
        sessionFactory.getCurrentSession().flush();
        Student student1 = daoStudent.saveStudent("John", "Snow", "snow@qgmail.com", 31);
        sessionFactory.getCurrentSession().flush();
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        daoCurs.inviteStudentOnCurs(student.getId(), curs.getId());
        sessionFactory.getCurrentSession().flush();
        daoCurs.inviteStudentOnCurs(student1.getId(), curs.getId());
        sessionFactory.getCurrentSession().flush();
        daoGradebook.addStudentToGradebook(student.getId(), curs.getId());
        sessionFactory.getCurrentSession().flush();
        daoGradebook.addStudentToGradebook(student1.getId(), curs.getId());
        sessionFactory.getCurrentSession().flush();
        List<Student> classmates = daoCurs.getClassmates(curs.getId());
        assertArrayEquals(classmates.toArray(), list.toArray());
    }

    @Test
    void getColleaguesTest(){
        Teacher teacher =  daoTeacher.saveTeacher("John", "Snow", "Winter@gmail.com", 1);
        Teacher teacher1 =  daoTeacher.saveTeacher("John", "Snoww", "Wwinter@gmail.com", 1);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        teachers.add(teacher1);
        assertArrayEquals(daoCurs.getColleagues(teacher.getCursId()).toArray(), teachers.toArray());
    }




}
