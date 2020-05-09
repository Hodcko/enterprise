package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.service.ServiceValidation;
import com.github.hodcko.multy.service.impl.ServiceValidationDefault;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceValidationDefaultTest {

    ServiceValidation serviceValidation = ServiceValidationDefault.getInstance();

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
