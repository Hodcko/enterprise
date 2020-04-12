package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.service.ISecurityService;
import com.github.hodcko.multy.service.IServiceAuthUser;
import com.github.hodcko.multy.service.impl.ServiceAuthUser;
import com.github.hodcko.multy.service.impl.ServiceAuthUserLogin;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceAuthUserTest {

    @Mock
    private static IDaoAuth iDaoAuth;

    @InjectMocks
    private static IServiceAuthUser iServiceAuthUser;

    @InjectMocks
    private static ISecurityService iSecurityService;

    @BeforeAll
    public static void createInstance() {
        iSecurityService = ServiceAuthUserLogin.getInstance();
        iServiceAuthUser = ServiceAuthUser.getInstance();
    }

    @Test
    void saveAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", "mockRole", 1);
        when(iDaoAuth.saveAuthUser(1, "mockLogin", "mockPassword", "mockRole")).thenReturn(authUserStudent);
        AuthUser authUser = iServiceAuthUser.saveAuthUser(1,"mockLogin", "mockPassword", "mockRole");
        Assertions.assertEquals(authUserStudent, authUser);

    }

    @Test
    void getRoleTest() {
        when(iDaoAuth.getRole("mockLogin", "mockPassword")).thenReturn("mockRole");
        String role = iServiceAuthUser.getRole("mockLogin", "mockPassword");
        Assertions.assertEquals(role, "mockRole");

    }

    @Test
    void getAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", "mockRole", 1);
        when(iDaoAuth.getAuthUser("mockLogin", "mockPassword")).thenReturn(authUserStudent);
        AuthUser authUserTest = iServiceAuthUser.getAuthUser("mockLogin", "mockPassword");
        Assertions.assertEquals(authUserStudent, authUserTest);
    }

    @Test
    void changePasswordTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", "mockRole", 1);
        when(iDaoAuth.changePassword("mockLogin","mockPassword", "qwerty" )).thenReturn(true);
        Boolean result = iSecurityService.changePassword(authUserStudent.getLogin(), authUserStudent.getPassword(), "qwerty");
        Assertions.assertEquals(true, result);
    }

}
