package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoIsExist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoIsExistTest {
    final IDaoIsExist iDaoIsExist = DaoIsExist.getInstance();

    @Test
    void isExistTest() {
        boolean studentResult = iDaoIsExist.isExist("hodckoq@mail.com", "student");
        boolean teacherResult =  iDaoIsExist.isExist("hodckoq@mail.com", "teacher");
        assertTrue(!teacherResult);
        assertTrue(!teacherResult);


    }
}
