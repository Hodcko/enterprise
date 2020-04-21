package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.service.SecurityService;
import com.github.hodcko.multy.service.ServiceAuthUser;
import com.github.hodcko.multy.service.impl.ServiceAuthUserDefault;
import com.github.hodcko.multy.service.impl.SecurityServiceDefault;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceAuthUserDefaultTest {

    @Mock
    private static DaoAuthUser daoAuthUser;

    @InjectMocks
    private static ServiceAuthUser serviceAuthUser;

    @InjectMocks
    private static SecurityService securityService;

    @BeforeAll
    public static void createInstance() {
        securityService = SecurityServiceDefault.getInstance();
        serviceAuthUser = ServiceAuthUserDefault.getInstance();
    }

    @Test
    void saveAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", "mockRole", 1);
        when(daoAuthUser.saveAuthUser(1, "mockLogin", "mockPassword", "mockRole")).thenReturn(authUserStudent);
        AuthUser authUser = serviceAuthUser.saveAuthUser(1,"mockLogin", "mockPassword", "mockRole");
        Assertions.assertEquals(authUserStudent, authUser);

    }

    @Test
    void getRoleTest() {
        when(daoAuthUser.getRole("mockLogin", "mockPassword")).thenReturn("mockRole");
        String role = serviceAuthUser.getRole("mockLogin", "mockPassword");
        Assertions.assertEquals(role, "mockRole");

    }

    @Test
    void getAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", "mockRole", 1);
        when(daoAuthUser.getAuthUser("mockLogin", "mockPassword")).thenReturn(authUserStudent);
        AuthUser authUserTest = serviceAuthUser.getAuthUser("mockLogin", "mockPassword");
        Assertions.assertEquals(authUserStudent, authUserTest);
    }

    @Test
    void changePasswordTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", "mockRole", 1);
        when(daoAuthUser.changePassword("mockLogin","mockPassword", "qwerty" )).thenReturn(true);
        Boolean result = securityService.changePassword(authUserStudent.getLogin(), authUserStudent.getPassword(), "qwerty");
        Assertions.assertEquals(true, result);
    }

}
