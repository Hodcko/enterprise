package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceGetIdByEmail;
import com.github.hodcko.multy.service.impl.ServiceGetIdByEmailDefault;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceGetIdTest {

    @Mock
    DaoStudent daoStudent;

    @Mock
    DaoTeacher daoTeacher;

    @InjectMocks
    ServiceGetIdByEmailDefault serviceGetIdByEmail;

    private final String email = "hodckoq@gmail.com";
    private final int result = 2;



    @Test
    void getIdTest(){
        when(daoStudent.getId(email)).thenReturn(result);
        int testResult =  serviceGetIdByEmail.getId(email, UserType.STUDENT);
        assertEquals(result, testResult);
    }

    @Test
    void getIdTest2(){
        when(daoTeacher.getId(email)).thenReturn(2);
        int testResult =  serviceGetIdByEmail.getId(email, UserType.TEACHER);
        assertEquals(result, testResult);
    }


}
