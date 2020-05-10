package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.service.SecurityService;
import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.impl.ServiceAuthUserDefault;
import com.github.hodcko.multy.service.impl.SecurityServiceDefault;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceAuthUserDefaultLoginTest {

    @Mock
    private static DaoAuthUser daoAuthUser;

    @InjectMocks
    private static ServiceAuthUser serviceAuthUser;

    @InjectMocks
    private static SecurityService securityService;



    @BeforeAll
    public static void createInstance() {
        serviceAuthUser = ServiceAuthUserDefault.getInstance();
        securityService = SecurityServiceDefault.getInstance();
    }


    @Test
    void findPasswordTest(){
        String password = securityService.findPassword(null, "password");
        assertEquals(password, "password");
    }

    @Test
    void loginTest(){
        when(daoAuthUser.getLoginByPassword("mockLogin")).thenReturn("mockLogin");
        String login = securityService.login("mockLogin", "mockLogin");
        assertEquals("mockLogin", login);
    }

    @Test
    void loginTestNull(){
        when(daoAuthUser.getLoginByPassword("mockLogin")).thenReturn(null);
        String login = serviceAuthUser.getLoginByPassword("mockLogin");
        assertNull(login);
    }



}
