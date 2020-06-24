package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.service.impl.ServiceAuthUserDefault;
import com.github.hodcko.multy.service.impl.SecurityServiceDefault;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceAuthUserDefaultLoginTest {


    @Mock
    private DaoAuthUser daoAuthUser;

    @InjectMocks
    private ServiceAuthUserDefault serviceAuthUser;

    @InjectMocks
    private SecurityServiceDefault securityService;

    private final String userLogin = "mockLogin";
    private final String userPassword = "password";




    @Test
    void loginTest(){
        when(daoAuthUser.getLoginByPassword(userLogin)).thenReturn(userLogin);
        String login = securityService.login(userLogin, userLogin);
        assertEquals(login, userLogin);
    }

    @Test
    void findPasswordTest(){
        String password = securityService.findPassword(null, userPassword);
        assertEquals(password, userPassword);
    }

    @Test
    void loginTestNull(){
        when(daoAuthUser.getLoginByPassword(userLogin)).thenReturn(null);
        String login = serviceAuthUser.getLoginByPassword(userLogin);
        assertNull(login);
    }



}
