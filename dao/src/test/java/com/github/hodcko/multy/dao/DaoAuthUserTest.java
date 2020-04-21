package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.impl.DaoUserAuth;
import com.github.hodcko.multy.dao.utils.AutoIncrementChanger;
import com.github.hodcko.multy.model.AuthUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DaoAuthUserTest {

    IDaoAuth iDaoAuth = DaoUserAuth.getInstance();

    @Test
    void saveAuthUserTest() {
        AuthUser authUserStudent = new AuthUser("John", "Snow", "student", 1);
        AuthUser authUseTEacherr = new AuthUser("John", "Snow", "teacher", 2);
        AuthUser studentTest = iDaoAuth.saveAuthUser(1,"John", "Snow", "student");
        AuthUser teacherTest = iDaoAuth.saveAuthUser(2,"John", "Snow", "teacher");
        iDaoAuth.deleteAuthUser(1, "student");
        iDaoAuth.deleteAuthUser(2, "teacher");
        Assertions.assertEquals(authUserStudent, studentTest);
        Assertions.assertEquals(authUseTEacherr, teacherTest);
    }

    @Test
    void getRoleTest() {
        AuthUser studentTest = iDaoAuth.saveAuthUser(3,"John", "Snow", "student");
        AuthUser teacherTest = iDaoAuth.saveAuthUser(4,"Jim", "Bill", "teacher");
        Assertions.assertEquals(studentTest.getRole(), iDaoAuth.getRole(studentTest.getLogin(), studentTest.getPassword()));
        Assertions.assertEquals(teacherTest.getRole(), iDaoAuth.getRole(teacherTest.getLogin(), teacherTest.getPassword()));
        iDaoAuth.deleteAuthUser(3, "student");
        iDaoAuth.deleteAuthUser(4, "teacher");
    }

    @Test
    void getAuthUserTest() {
        AuthUser studentTest = iDaoAuth.saveAuthUser(5,"John", "Snow", "student");
        AuthUser studentTestGEt = iDaoAuth.getAuthUser(studentTest.getLogin(), studentTest.getPassword());
        Assertions.assertEquals(studentTest, studentTestGEt);
        iDaoAuth.deleteAuthUser(5, "student");
    }

    @Test
    void changePasswordTest() {
        AuthUser studentTest = iDaoAuth.saveAuthUser(6,"John", "Snow", "student");
        iDaoAuth.changePassword(studentTest.getLogin(), studentTest.getPassword(), "qwerty");
        Assertions.assertEquals(iDaoAuth.getByLogin("qwerty"), studentTest.getLogin());
        iDaoAuth.deleteAuthUser(6, "student");
    }


}
