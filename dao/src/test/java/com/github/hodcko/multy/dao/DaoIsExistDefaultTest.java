package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoIsExistDefault;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoIsExistDefaultTest {
    final DaoIsExist daoIsExist = DaoIsExistDefault.getInstance();
    final DaoStudent daoStudent = DaoStudentDefault.getInstance();
    final DaoTeacher daoTeacher = DaoTeacherDefault.getInstance();



    @Test
    void isExistTest() {
        daoStudent.saveStudent("test", "test", "hodckoq@mail.com", 30, 1);
        daoTeacher.saveTeacher("test", "test", "hodckoq@mail.com",  1);
        boolean studentResult = daoIsExist.isExist("hodckoq@mail.com", "student");
        boolean teacherResult =  daoIsExist.isExist("hodckoq@mail.com", "teacher");
        daoStudent.deleteStudent("hodckoq@mail.com");
        daoTeacher.deleteTeacher("hodckoq@mail.com");
        assertTrue(teacherResult);
        assertTrue(studentResult);
    }
}
