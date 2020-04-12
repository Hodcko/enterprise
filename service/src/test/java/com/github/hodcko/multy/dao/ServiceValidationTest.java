package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.service.IServiceValidation;
import com.github.hodcko.multy.service.impl.ServiceValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceValidationTest {

    IServiceValidation iServiceValidation = ServiceValidation.getInstance();

    @Test
    void validationStudentTest(){
        boolean result = true;
        boolean resultTest = iServiceValidation.validationStudent("Jonh", "Snow", "winter@gmail.com", 30);
        assertEquals(result, resultTest);
    }

    @Test
    void validationTeacherTest(){
        boolean result = true;
        boolean resultTest = iServiceValidation.validationTeacher("Jonh", "Snow", "winter@gmail.com");
        assertEquals(result, resultTest);
    }
}
