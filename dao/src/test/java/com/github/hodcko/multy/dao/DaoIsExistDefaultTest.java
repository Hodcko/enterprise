package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoIsExistDefault;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoIsExistDefaultTest {
    final DaoIsExist daoIsExist = DaoIsExistDefault.getInstance();

    @Test
    void isExistTest() {
        boolean studentResult = daoIsExist.isExist("hodckoq@mail.com", "student");
        boolean teacherResult =  daoIsExist.isExist("hodckoq@mail.com", "teacher");
        assertTrue(!teacherResult);
        assertTrue(!teacherResult);


    }
}
