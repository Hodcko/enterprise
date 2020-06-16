package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.config.DaoConfig;
import com.github.hodcko.multy.dao.converter.AuthUserConverter;
import com.github.hodcko.multy.dao.entity.AuthUserEntity;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
    @Autowired
    private SessionFactory sessionFactory;




    @Test
    void saveAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("John", "Snow", UserType.STUDENT, 1);
        AuthUser authUseTEacherr = new AuthUser("John", "Snow", UserType.TEACHER, 2);
        AuthUser studentTest = daoAuthUser.saveAuthUser(1,"John", "Snow", UserType.STUDENT);
        AuthUser teacherTest = daoAuthUser.saveAuthUser(2,"John", "Snow", UserType.TEACHER);
        Assertions.assertEquals(authUserStudent, studentTest);
        Assertions.assertEquals(authUseTEacherr, teacherTest);
    }

    @Test
    void getRoleTest() {
        AuthUser studentTest = daoAuthUser.saveAuthUser(3,"John", "Snow", UserType.STUDENT);
        AuthUser teacherTest = daoAuthUser.saveAuthUser(4,"Jim", "Bill", UserType.TEACHER);
        Assertions.assertEquals(studentTest.getRole(), daoAuthUser.getRole(studentTest.getLogin(), studentTest.getPassword()));
        Assertions.assertEquals(teacherTest.getRole(), daoAuthUser.getRole(teacherTest.getLogin(), teacherTest.getPassword()));
    }

    @Test
    void getAuthUserTest() {
        AuthUser studentTest = daoAuthUser.saveAuthUser(5,"John", "Snow", UserType.STUDENT);
        AuthUser studentTestGEt = daoAuthUser.getAuthUser(studentTest.getLogin(), studentTest.getPassword());
        Assertions.assertEquals(studentTest, studentTestGEt);
    }

    @Test
    void changePasswordTest() {
        AuthUser studentTest = daoAuthUser.saveAuthUser(6,"John", "Snow", UserType.STUDENT);
        daoAuthUser.changePassword(studentTest.getLogin(), studentTest.getPassword(), "qwerty");
        Assertions.assertEquals(daoAuthUser.getLoginByPassword("qwerty"), studentTest.getLogin());
    }

    @Test
    void deleteAuthUserTest(){
        AuthUser authUserStudent = new AuthUser("John", "Snow", UserType.STUDENT, 1);
        daoAuthUser.saveAuthUser(authUserStudent.getUserId(), authUserStudent.getLogin(), authUserStudent.getPassword(),
                authUserStudent.getRole());
        daoAuthUser.deleteAuthUser(authUserStudent.getUserId(), authUserStudent.getRole());
        assertNull(daoAuthUser.getAuthUser(authUserStudent.getLogin(), authUserStudent.getPassword()));

    }

    @Test
    void getLoginByPasswordTest(){
        AuthUser studentTest = daoAuthUser.saveAuthUser(6,"John", "Snow", UserType.STUDENT);
        Assertions.assertEquals(daoAuthUser.getLoginByPassword("Snow"), studentTest.getLogin());
    }

    @Test
    void authUserConverterTest(){
        AuthUser studentTest = daoAuthUser.saveAuthUser(6,"John", "Snow", UserType.STUDENT);
        AuthUserEntity authUserEntity = AuthUserConverter.toEntity(studentTest);
        AuthUser authUser = AuthUserConverter.fromEntity(authUserEntity);
        assertNotNull(authUserEntity);
        assertNotNull(authUser);
    }
}
