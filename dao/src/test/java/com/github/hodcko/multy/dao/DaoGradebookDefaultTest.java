package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.config.DaoConfig;
import com.github.hodcko.multy.dao.converter.GradebookConverter;
import com.github.hodcko.multy.dao.entity.GradebookEntity;
import com.github.hodcko.multy.model.Gradebook;
import com.github.hodcko.multy.model.Student;
import org.junit.jupiter.api.BeforeEach;
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


    private final int cursId = 1;
    private final int grade = 1;
    private final Gradebook gradebook = new Gradebook(1, 1, 1, 5);
    private final GradebookEntity gradebookEntity = new GradebookEntity(1, 1, 1, 5);
    private Student student;

    @BeforeEach
    void saver(){
        student =  daoStudent.saveStudent("John", "Snow", "Winter@mail.ru", 31);
    }


    @Test
    void addStudentToGradebookTest() {
        int result = daoGradebook.addStudentToGradebook(student.getId(), cursId);
        assertEquals(student.getId(), result);
    }

    @Test
    void addGradeTest() {
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        int result = daoGradebook.addGrade(student.getId(), cursId);
        assertEquals(student.getId(), result);
    }

    @Test
    void getGradeTest() {
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        daoGradebook.addGrade(student.getId(), cursId);
        int result = daoGradebook.getGrade(student.getId(), cursId);
        assertEquals(grade, result);
    }

    @Test
    void deleteStudentFromGradebookTest(){
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        daoGradebook.deleteStudentFromGradebook(student.getId());
        assertFalse(daoGradebook.isExist(student.getId()));
    }

    @Test
    void isExistTest(){
        daoGradebook.addStudentToGradebook(student.getId(), cursId);
        assertTrue(daoGradebook.isExist(student.getId()));
    }

    @Test
    void gradebookConverterToEntityTest(){
        GradebookEntity gradebookEntity = GradebookConverter.toEntity(gradebook);
        assertNotNull(gradebookEntity);
    }

    @Test
    void gradebookConverterTFromEntityTest(){
        Gradebook testGradebook = GradebookConverter.fromEntity(gradebookEntity);
        assertNotNull(testGradebook);
    }
}
