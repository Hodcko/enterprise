package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceCurs;
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
    private ServiceStudent serviceStudent;

    @Mock
    private ServiceTeacher serviceTeacher;

    @Mock
    private ServiceCurs serviceCurs;

    @InjectMocks
    private ServiceRegistrationDefault serviceRegistrationDefault;

    @Test
    void registrationTest2(){
        Student student = new Student(1, "John", "Snow", "Winter@gmail.com", 33);
        when(serviceStudent.saveStudent(student.getName(), student.getSecondName(), student.getEmail(), student.getAge())).thenReturn(student);
        boolean result = serviceRegistrationDefault.registration(student.getName(), student.getSecondName(), student.getEmail(), student.getAge(), UserType.STUDENT, "java");
        assertTrue(result);
    }

    @Test
    void registrationTest(){
        Teacher teacher = new Teacher(1, "John", "Snow", "Winter@gmail.com",  1);
        when(serviceCurs.getCursId("java")).thenReturn(1);
        when(serviceTeacher.saveTeacher(teacher.getName(), teacher.getSecondName(), teacher.getEmail(), teacher.getCursId())).thenReturn(teacher);
        boolean result = serviceRegistrationDefault.registration(teacher.getName(), teacher.getSecondName(), teacher.getEmail(), teacher.getCursId(), UserType.TEACHER, "java");
        assertTrue(result);
    }


}
