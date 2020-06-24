package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.config.ServiceValidationConfigTest;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.service.ServiceValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceValidationConfigTest.class)
public class ServiceValidationDefaultTest {


    @Autowired
    private ServiceValidation serviceValidation;

    private final Student student = new Student("Jonh", "Snow", "winter@gmail.com", 30);
    private final Teacher teacher = new Teacher(1, "Jonh", "Snow", "winter@gmail.com" ,1 );
    private final boolean result = true;



    @Test
    void validationStudentTest(){
        boolean resultTest = serviceValidation.validationStudent(student.getName(), student.getSecondName(), student.getEmail(), student.getAge());
        assertEquals(result, resultTest);
    }

    @Test
    void validationTeacherTest(){
        boolean resultTest = serviceValidation.validationTeacher(teacher.getName(), teacher.getSecondName(), teacher.getEmail());
        assertEquals(result, resultTest);
    }
}
