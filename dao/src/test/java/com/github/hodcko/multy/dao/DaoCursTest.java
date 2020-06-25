package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.config.DaoConfig;

import com.github.hodcko.multy.dao.converter.CursConverter;
import com.github.hodcko.multy.dao.entity.CursEntity;
import com.github.hodcko.multy.dao.entity.GroupDTO;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    private Curs testCurs;
    private Student student;
    private Student student2;
    private Teacher teacher;
    private Teacher teacher1;
    private final int countOfStudents = 1;
    private final int numOfPage = 1;


    @BeforeEach
    void saver() {
        testCurs = daoCurs.createCurs("Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        student = daoStudent.saveStudent("Jogn", "Snow", "snow@gmail.com", 31);
        student2 = daoStudent.saveStudent("John", "Snow", "snow@qgmail.com", 31);
        teacher =  daoTeacher.saveTeacher("John", "Snow", "Winter@gmail.com", 1);
        teacher1 =  daoTeacher.saveTeacher("John", "Snoww", "Wwinter@gmail.com", 1);
    }


    private final CursEntity cursEntity = new CursEntity(1,"Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
    private final Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));


    @Test
    void createCursTest() {
        assertEquals(curs, testCurs);
    }

    @Test
    void getCursTest() {
        assertEquals(testCurs, daoCurs.getCurs(testCurs.getId()));
    }

    @Test
    void deleteCurs(){
        daoCurs.deleteCurs(testCurs.getId());
        sessionFactory.getCurrentSession().clear();
        assertNull(daoCurs.getCurs(testCurs.getId()).getEnd());
    }

    @Test
    void getMyStudentsTest(){
        daoGradebook.addStudentToGradebook(student.getId(), curs.getId());
        daoGradebook.addGrade(student.getId(), curs.getId());
        daoCurs.inviteStudentOnCurs(student.getId(), curs.getId());
        GroupDTO groupDTO = new GroupDTO(student.getName(), student.getSecondName(), student.getEmail(), daoGradebook.getGrade(student.getId(), curs.getId()));
        List<GroupDTO> list = new ArrayList<>();
        list.add(groupDTO);
        assertEquals(daoCurs.getMyStudents(curs.getId(),numOfPage), list);
    }

    @Test
    void countOfStudentsTest(){
        daoGradebook.addStudentToGradebook(student.getId(), curs.getId());
        daoGradebook.addGrade(student.getId(), curs.getId());
        daoCurs.inviteStudentOnCurs(student.getId(), curs.getId());
        GroupDTO groupDTO = new GroupDTO(student.getName(), student.getSecondName(), student.getEmail(), daoGradebook.getGrade(student.getId(), curs.getId()));
        List<GroupDTO> list = new ArrayList<>();
        list.add(groupDTO);
        assertEquals(daoCurs.countOfStudents(curs.getId()), countOfStudents);
    }

    @Test
    void getClassmatesTest(){
        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student2);
        daoCurs.inviteStudentOnCurs(student.getId(), curs.getId());
        sessionFactory.getCurrentSession().flush();
        daoCurs.inviteStudentOnCurs(student2.getId(), curs.getId());
        sessionFactory.getCurrentSession().flush();
        daoGradebook.addStudentToGradebook(student.getId(), curs.getId());
        sessionFactory.getCurrentSession().flush();
        daoGradebook.addStudentToGradebook(student2.getId(), curs.getId());
        sessionFactory.getCurrentSession().flush();
        List<Student> classmates = daoCurs.getClassmates(curs.getId());
        assertArrayEquals(classmates.toArray(), list.toArray());
    }

    @Test
    void getColleaguesTest(){
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        teachers.add(teacher1);
        assertArrayEquals(daoCurs.getColleagues(teacher.getCursId()).toArray(), teachers.toArray());
    }

    @Test
    void cursConverterToEntityTest(){
        CursEntity cursEntity = CursConverter.toEntity(testCurs);
        assertNotNull(cursEntity);
    }

    @Test
    void cursConverterFromEntityTest(){
        Curs curs = CursConverter.fromEntity(cursEntity);
        assertNotNull(curs);
    }
}
