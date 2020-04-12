package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.service.ISecurityService;
import com.github.hodcko.multy.service.IServiceAuthUser;
import com.github.hodcko.multy.service.impl.ServiceAuthUser;
import com.github.hodcko.multy.service.impl.ServiceAuthUserLogin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceAuthUserLoginTest {

    @Mock
    private static IDaoAuth iDaoAuth;

    @InjectMocks
    private static IServiceAuthUser iServiceAuthUser;

    @InjectMocks
    private static ISecurityService iSecurityService;



    @BeforeAll
    public static void createInstance() {
        iServiceAuthUser = ServiceAuthUser.getInstance();
        iSecurityService = ServiceAuthUserLogin.getInstance();
    }


    @Test
    void findPasswordTest(){
        String password = iSecurityService.findPassword(null, "password");
        assertEquals(password, "password");
    }

    @Test
    void loginTest(){
        when(iDaoAuth.getByLogin("mockLogin")).thenReturn("mockLogin");
        iServiceAuthUser.saveAuthUser(1, "mockLogin", "mockPassword", "mockRole");
        String login = iServiceAuthUser.getByLogin("mockLogin");
        assertEquals("mockLogin", login);

    }



}
