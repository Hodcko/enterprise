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


    @Test
    void getIdTest(){
        String email = "hodckoq@gmail.com";
        when(daoStudent.getId(email,UserType.STUDENT)).thenReturn(2);
        int testResult =  serviceGetIdByEmail.getId(email, UserType.STUDENT);
        assertEquals(2, testResult);
    }

    @Test
    void getIdTest2(){
        String email = "hodckoq@gmail.com";
        when(daoTeacher.getId(email,UserType.TEACHER)).thenReturn(2);
        int testResult =  serviceGetIdByEmail.getId(email, UserType.TEACHER);
        assertEquals(2, testResult);
    }


}
