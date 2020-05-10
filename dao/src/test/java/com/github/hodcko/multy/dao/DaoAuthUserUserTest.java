package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.impl.DaoAuthUserDefault;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DaoAuthUserUserTest {

    final DaoAuthUser daoAuthUser = DaoAuthUserDefault.getInstance();



    @Test
    void saveAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("John", "Snow", UserType.STUDENT, 1);
        AuthUser authUseTEacherr = new AuthUser("John", "Snow", UserType.TEACHER, 2);
        AuthUser studentTest = daoAuthUser.saveAuthUser(1,"John", "Snow", UserType.STUDENT);
        AuthUser teacherTest = daoAuthUser.saveAuthUser(2,"John", "Snow", UserType.TEACHER);
        daoAuthUser.deleteAuthUser(1, UserType.STUDENT);
        daoAuthUser.deleteAuthUser(2, UserType.TEACHER);
        Assertions.assertEquals(authUserStudent, studentTest);
        Assertions.assertEquals(authUseTEacherr, teacherTest);
    }

    @Test
    void getRoleTest() {
        AuthUser studentTest = daoAuthUser.saveAuthUser(3,"John", "Snow", UserType.STUDENT);
        AuthUser teacherTest = daoAuthUser.saveAuthUser(4,"Jim", "Bill", UserType.TEACHER);
        Assertions.assertEquals(studentTest.getRole(), daoAuthUser.getRole(studentTest.getLogin(), studentTest.getPassword()));
        Assertions.assertEquals(teacherTest.getRole(), daoAuthUser.getRole(teacherTest.getLogin(), teacherTest.getPassword()));
        daoAuthUser.deleteAuthUser(3, UserType.STUDENT);
        daoAuthUser.deleteAuthUser(4, UserType.TEACHER);
    }

    @Test
    void getAuthUserTest() {
        AuthUser studentTest = daoAuthUser.saveAuthUser(5,"John", "Snow", UserType.STUDENT);
        AuthUser studentTestGEt = daoAuthUser.getAuthUser(studentTest.getLogin(), studentTest.getPassword());
        Assertions.assertEquals(studentTest, studentTestGEt);
        daoAuthUser.deleteAuthUser(5, UserType.STUDENT);
    }

    @Test
    void changePasswordTest() {
        AuthUser studentTest = daoAuthUser.saveAuthUser(6,"John", "Snow", UserType.STUDENT);
        daoAuthUser.changePassword(studentTest.getLogin(), studentTest.getPassword(), "qwerty");
        Assertions.assertEquals(daoAuthUser.getLoginByPassword("qwerty"), studentTest.getLogin());
        daoAuthUser.deleteAuthUser(6, UserType.STUDENT);
    }

    @Test
    void getLoginByPasswordTest(){
        AuthUser studentTest = daoAuthUser.saveAuthUser(6,"John", "Snow", UserType.STUDENT);
        Assertions.assertEquals(daoAuthUser.getLoginByPassword("Snow"), studentTest.getLogin());
        daoAuthUser.deleteAuthUser(6, UserType.STUDENT);
    }
}
