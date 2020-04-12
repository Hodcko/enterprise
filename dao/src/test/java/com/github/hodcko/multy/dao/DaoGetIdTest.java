package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.impl.DaoGetIdByEmail;


import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoGetIdTest {
    final IDaoGetIdByEmail iDaoGetIdByEmail = DaoGetIdByEmail.getInstance();




    @Test
    void createCursTest() {
        int studentId = iDaoGetIdByEmail.getId("hodckoq@gmail.com", "student");
        int teacherId = iDaoGetIdByEmail.getId("hodckoq@gmail.com", "teacher");
        assertEquals(1, studentId);
        assertEquals(2, teacherId);

    }

}
