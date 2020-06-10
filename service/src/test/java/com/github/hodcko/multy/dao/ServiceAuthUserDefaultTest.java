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


    @Test
    void saveAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", UserType.STUDENT, 1);
        when(daoAuthUser.saveAuthUser(1, "mockLogin", "mockPassword", UserType.STUDENT)).thenReturn(authUserStudent);
        AuthUser authUser = serviceAuthUser.saveAuthUser(1,"mockLogin", "mockPassword", UserType.STUDENT);
        Assertions.assertEquals(authUserStudent, authUser);

    }

    @Test
    void getRoleTest() {
        when(daoAuthUser.getRole("mockLogin", "mockPassword")).thenReturn(UserType.STUDENT);
        UserType role = serviceAuthUser.getRole("mockLogin", "mockPassword");
        Assertions.assertEquals(role, UserType.STUDENT);

    }

    @Test
    void getAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", UserType.STUDENT, 1);
        when(daoAuthUser.getAuthUser("mockLogin", "mockPassword")).thenReturn(authUserStudent);
        AuthUser authUserTest = serviceAuthUser.getAuthUser("mockLogin", "mockPassword");
        Assertions.assertEquals(authUserStudent, authUserTest);
    }

    @Test
    void changePasswordTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", UserType.STUDENT, 1);
        when(daoAuthUser.changePassword("mockLogin","mockPassword", "qwerty" )).thenReturn(true);
        Boolean result = securityService.changePassword(authUserStudent.getLogin(), authUserStudent.getPassword(), "qwerty");
        Assertions.assertEquals(true, result);
    }

    @Test
    void passwordGenerateTest() {
        Student student = new Student(1, "John", "Snow", "Snow@gmail.com", 31);
        when(serviceAuthUser.passwordGenerate("Snow@gmail.com", UserType.STUDENT )).thenReturn("Snow1");
        String password = serviceAuthUser.passwordGenerate(student.getEmail(), UserType.STUDENT);
        Assertions.assertEquals("Snow1", password);
    }

    @Test
    void deleteAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("mockLogin", "mockPassword", UserType.STUDENT, 1);
        when(serviceAuthUser.deleteAuthUser(authUserStudent.getUserId(), authUserStudent.getRole())).thenReturn(true);
        boolean result = serviceAuthUser.deleteAuthUser(authUserStudent.getUserId(), authUserStudent.getRole());
        Assertions.assertTrue(result);
    }
}
