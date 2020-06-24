package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.config.ServiceConfig;
import com.github.hodcko.multy.service.impl.ServiceAuthUserDefault;
import com.github.hodcko.multy.service.impl.SecurityServiceDefault;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceAuthUserDefaultTest {

    @Mock
    private DaoAuthUser daoAuthUser;

    @Mock
    private DaoStudent daoStudent;

    @InjectMocks
    private ServiceAuthUserDefault serviceAuthUser;

    @InjectMocks
    private SecurityServiceDefault securityService;

    private final AuthUser aUS = new AuthUser("mockLogin", "mockPassword", UserType.STUDENT, 1);
    private final Student student = new Student(1, "John", "Snow", "Snow@gmail.com", 31);
    private final String newPassword = "qwerty";
    private final String userPassword = "qwerty";




    @Test
    void saveAuthUserTest() {
        when(daoAuthUser.saveAuthUser(1, aUS.getLogin(), aUS.getPassword(), UserType.STUDENT)).thenReturn(aUS);
        AuthUser authUser = serviceAuthUser.saveAuthUser(1,aUS.getLogin(), aUS.getPassword(), UserType.STUDENT);
        Assertions.assertEquals(aUS, authUser);

    }

    @Test
    void getRoleTest() {
        when(daoAuthUser.getRole(aUS.getLogin(), aUS.getPassword())).thenReturn(UserType.STUDENT);
        UserType role = serviceAuthUser.getRole(aUS.getLogin(), aUS.getPassword());
        Assertions.assertEquals(role, UserType.STUDENT);

    }

    @Test
    void getAuthUserTest() {
        when(daoAuthUser.getAuthUser(aUS.getLogin(), aUS.getPassword())).thenReturn(aUS);
        AuthUser authUserTest = serviceAuthUser.getAuthUser(aUS.getLogin(), aUS.getPassword());
        Assertions.assertEquals(aUS, authUserTest);
    }

    @Test
    void changePasswordTest() {
        when(daoAuthUser.changePassword(aUS.getLogin(), aUS.getPassword(), newPassword )).thenReturn(true);
        Boolean result = securityService.changePassword(aUS.getLogin(), aUS.getPassword(), newPassword);
        Assertions.assertEquals(true, result);
    }

    @Test
    void passwordGenerateTest() {
        when(serviceAuthUser.passwordGenerate(student.getEmail(), UserType.STUDENT )).thenReturn(userPassword);
        String password = serviceAuthUser.passwordGenerate(student.getEmail(), UserType.STUDENT);
        Assertions.assertEquals(userPassword, password);
    }

    @Test
    void deleteAuthUserTest() {
        when(serviceAuthUser.deleteAuthUser(aUS.getUserId(), aUS.getRole())).thenReturn(true);
        boolean result = serviceAuthUser.deleteAuthUser(aUS.getUserId(), aUS.getRole());
        Assertions.assertTrue(result);
    }
}
