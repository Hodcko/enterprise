package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.config.DaoConfig;
import com.github.hodcko.multy.dao.converter.AuthUserConverter;
import com.github.hodcko.multy.dao.entity.AuthUserEntity;
import com.github.hodcko.multy.dao.entity.TeacherEntity;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DaoAuthUserUserTest {

    @Autowired
    private DaoAuthUser daoAuthUser;

    private AuthUser studentTest;
    private AuthUser teacherTest;
    private final String newPassword = "qwerty";


    @BeforeEach
    void saver() {
        studentTest = daoAuthUser.saveAuthUser(3, "John", "Snow", UserType.STUDENT);
        teacherTest = daoAuthUser.saveAuthUser(4, "Jim", "Bill", UserType.TEACHER);
    }


    @Test
    void saveAuthUserTest() {
        AuthUser student = daoAuthUser.saveAuthUser(studentTest.getUserId(), studentTest.getLogin(),
                studentTest.getPassword(), UserType.STUDENT);
        AuthUser teacher = daoAuthUser.saveAuthUser(teacherTest.getUserId(), teacherTest.getLogin(),
                teacherTest.getPassword(), UserType.TEACHER);
        Assertions.assertEquals(student, studentTest);
        Assertions.assertEquals(teacher, teacherTest);
    }

    @Test
    void getRoleTest() {
        Assertions.assertEquals(studentTest.getRole(), daoAuthUser.getRole(studentTest.getLogin(), studentTest.getPassword()));
        Assertions.assertEquals(teacherTest.getRole(), daoAuthUser.getRole(teacherTest.getLogin(), teacherTest.getPassword()));
    }

    @Test
    void getAuthUserTest() {
        AuthUser studentTestGEt = daoAuthUser.getAuthUser(studentTest.getLogin(), studentTest.getPassword());
        Assertions.assertEquals(studentTest, studentTestGEt);
    }

    @Test
    void changePasswordTest() {
        daoAuthUser.changePassword(studentTest.getLogin(), studentTest.getPassword(), newPassword);
        Assertions.assertEquals(daoAuthUser.getLoginByPassword(newPassword), studentTest.getLogin());
    }

    @Test
    void deleteAuthUserTest() {
        daoAuthUser.saveAuthUser(studentTest.getUserId(), studentTest.getLogin(), studentTest.getPassword(),
                studentTest.getRole());
        daoAuthUser.deleteAuthUser(studentTest.getUserId(), studentTest.getRole());
        assertNull(daoAuthUser.getAuthUser(studentTest.getLogin(), studentTest.getPassword()));

    }

    @Test
    void getLoginByPasswordTest() {
        Assertions.assertEquals(daoAuthUser.getLoginByPassword(studentTest.getPassword()), studentTest.getLogin());
    }

    @Test
    void authUserConverterTest() {
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(studentTest);
        AuthUser authUser = AuthUserConverter.fromEntity(authUserEntity);
        assertNotNull(authUserEntity);
        assertNotNull(authUser);
    }
}
