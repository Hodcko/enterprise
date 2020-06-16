package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.config.DaoConfig;
import com.github.hodcko.multy.dao.converter.GradebookConverter;
import com.github.hodcko.multy.dao.entity.GradebookEntity;
import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.model.Gradebook;
import com.github.hodcko.multy.model.Student;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DaoGradebookDefaultTest {

    @Autowired
    private DaoGradebook daoGradebook;
    @Autowired
    private DaoStudent daoStudent;
    @Autowired
    private SessionFactory sessionFactory;


    private int cursId = 1;

    @Test
    void addStudentToGradebookTest() {
        Student student =  daoStudent.saveStudent("John", "Snow", "Winter@mail.ru", 31);
        int result = daoGradebook.addStudentToGradebook(student.getId(), cursId);
        assertEquals(student.getId(), result);
    }

    @Test
    void addGradeTest() {
        Student student =  daoStudent.saveStudent("John", "Snow", "Winter@mail.ru", 31);
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        int result = daoGradebook.addGrade(student.getId(), cursId);
        assertEquals(student.getId(), result);
    }

    @Test
    void getGradeTest() {
        Student student =  daoStudent.saveStudent("John", "Snow", "Winter@mail.ru", 31);
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        daoGradebook.addGrade(student.getId(), cursId);
        int result = daoGradebook.getGrade(student.getId(), cursId);
        assertEquals(1, result);
    }

    @Test
    void deleteStudentFromGradebookTest(){
        Student student =  daoStudent.saveStudent("John", "Snow", "Winter@mail.ru", 31);
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        daoGradebook.deleteStudentFromGradebook(student.getId(), cursId);
        assertFalse(daoGradebook.isExist(student.getId()));
    }

    @Test
    void isExistTest(){
        Student student =  daoStudent.saveStudent("John", "Snow", "Winter@mail.ru", 31);
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        assertTrue(daoGradebook.isExist(student.getId()));
    }

    @Test
    void gradebookConverterToEntityTest(){
        Gradebook gradebook = new Gradebook(1, 1, 1, 5);
        GradebookEntity gradebookEntity = GradebookConverter.toEntity(gradebook);
        assertNotNull(gradebookEntity);
    }

    @Test
    void gradebookConverterTFromEntityTest(){
        GradebookEntity gradebookEntity = new GradebookEntity(1, 1, 1, 5);
        Gradebook testGradebook = GradebookConverter.fromEntity(gradebookEntity);
        assertNotNull(testGradebook);
    }


}
