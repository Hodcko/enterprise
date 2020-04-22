package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.impl.DaoGetIdByEmailDefault;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoGetIdTest {
    final DaoGetIdByEmail daoGetIdByEmail = DaoGetIdByEmailDefault.getInstance();
    final DaoStudent daoStudent = DaoStudentDefault.getInstance();
    final DaoTeacher daoTeacher = DaoTeacherDefault.getInstance();



    @Test
    void setDaoGetIdByEmailTest() {
        Student student = daoStudent.saveStudent("test", "test", "hodckoq@mail.com", 30, 1);
        Teacher teacher = daoTeacher.saveTeacher("test", "test", "hodckoq@mail.com",  1);
        int studentId = daoGetIdByEmail.getId("hodckoq@mail.com", UserType.STUDENT);
        int teacherId = daoGetIdByEmail.getId("hodckoq@mail.com", UserType.TEACHER);
        daoStudent.deleteStudent("hodckoq@mail.com");
        daoTeacher.deleteTeacher("hodckoq@mail.com");
        assertEquals(student.getId(), studentId);
        assertEquals(teacher.getId(), teacherId);

    }

}
