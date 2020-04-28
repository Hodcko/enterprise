package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceStudent;
import com.github.hodcko.multy.service.ServiceTeacher;
import com.github.hodcko.multy.service.impl.ServiceRegistrationDefault;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceRegistrationDefaultTest {
    @Mock
    private static ServiceStudent serviceStudent;

    @Mock
    private static ServiceTeacher serviceTeacher;

    @InjectMocks
    private static ServiceRegistrationDefault serviceRegistrationDefault;

    @Test
    void registrationTest(){
        Student student = new Student("John", "Snow", "Winter@gmail.com", 33, 1);
        when(serviceStudent.saveStudent(student.getName(), student.getSecondName(), student.getEmail(), student.getAge(), student.getCurs_id())).thenReturn(student);
        boolean result = serviceRegistrationDefault.registration(student.getName(), student.getSecondName(), student.getEmail(), student.getAge(), UserType.STUDENT, "java");
        assertTrue(result);
    }

    @Test
    void registrationTest2(){
        Teacher teacher = new Teacher("John", "Snow", "Winter@gmail.com",  1);
        when(serviceTeacher.saveTeacher(teacher.getName(), teacher.getSecondName(), teacher.getEmail(), teacher.getCurs_id())).thenReturn(teacher);
        boolean result = serviceRegistrationDefault.registration(teacher.getName(), teacher.getSecondName(), teacher.getEmail(), teacher.getCurs_id(), UserType.TEACHER, "java");
        assertTrue(result);
    }


}
