package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.service.IServiceCurs;
import com.github.hodcko.multy.service.IServiceGetIdByEmail;
import com.github.hodcko.multy.service.impl.ServiceAuthUser;
import com.github.hodcko.multy.service.impl.ServiceAuthUserLogin;
import com.github.hodcko.multy.service.impl.ServiceGetIdByEmail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    private static IDaoGetIdByEmail IDGIBE;

    @InjectMocks
    private static IServiceGetIdByEmail ISGIBE;

    @BeforeAll
    public static void createInstance() {
        ISGIBE = ServiceGetIdByEmail.getInstance();
    }

    @Test
    void getIdTest(){
        String email = "hodckoq@gmail.com";
        String userType = "teacher";
        when(IDGIBE.getId(email,userType)).thenReturn(2);
        int testResult =  ISGIBE.getId(email, userType);
        assertEquals(2, testResult);
    }



}
