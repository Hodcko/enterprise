package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.config.ServiceValidationConfigTest;
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

    final boolean result = true;


    @Test
    void validationStudentTest(){
        boolean resultTest = serviceValidation.validationStudent("Jonh", "Snow", "winter@gmail.com", 30);
        assertEquals(result, resultTest);
    }

    @Test
    void validationTeacherTest(){
        boolean resultTest = serviceValidation.validationTeacher("Jonh", "Snow", "winter@gmail.com");
        assertEquals(result, resultTest);
    }
}
