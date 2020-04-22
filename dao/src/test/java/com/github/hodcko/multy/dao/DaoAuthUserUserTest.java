package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.impl.DaoAuthUserDefault;
import com.github.hodcko.multy.model.AuthUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DaoAuthUserUserTest {

    DaoAuthUser daoAuthUser = DaoAuthUserDefault.getInstance();

    @Test
    void saveAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("John", "Snow", "student", 1);
        AuthUser authUseTEacherr = new AuthUser("John", "Snow", "teacher", 2);
        AuthUser studentTest = daoAuthUser.saveAuthUser(1,"John", "Snow", "student");
        AuthUser teacherTest = daoAuthUser.saveAuthUser(2,"John", "Snow", "teacher");
        daoAuthUser.deleteAuthUser(1, "student");
        daoAuthUser.deleteAuthUser(2, "teacher");
        Assertions.assertEquals(authUserStudent, studentTest);
        Assertions.assertEquals(authUseTEacherr, teacherTest);
    }

    @Test
    void getRoleTest() {
        AuthUser studentTest = daoAuthUser.saveAuthUser(3,"John", "Snow", "student");
        AuthUser teacherTest = daoAuthUser.saveAuthUser(4,"Jim", "Bill", "teacher");
        Assertions.assertEquals(studentTest.getRole(), daoAuthUser.getRole(studentTest.getLogin(), studentTest.getPassword()));
        Assertions.assertEquals(teacherTest.getRole(), daoAuthUser.getRole(teacherTest.getLogin(), teacherTest.getPassword()));
        daoAuthUser.deleteAuthUser(3, "student");
        daoAuthUser.deleteAuthUser(4, "teacher");
    }

    @Test
    void getAuthUserTest() {
        AuthUser studentTest = daoAuthUser.saveAuthUser(5,"John", "Snow", "student");
        AuthUser studentTestGEt = daoAuthUser.getAuthUser(studentTest.getLogin(), studentTest.getPassword());
        Assertions.assertEquals(studentTest, studentTestGEt);
        daoAuthUser.deleteAuthUser(5, "student");
    }

    @Test
    void changePasswordTest() {
        AuthUser studentTest = daoAuthUser.saveAuthUser(6,"John", "Snow", "student");
        daoAuthUser.changePassword(studentTest.getLogin(), studentTest.getPassword(), "qwerty");
        Assertions.assertEquals(daoAuthUser.getByLogin("qwerty"), studentTest.getLogin());
        daoAuthUser.deleteAuthUser(6, "student");
    }


}
