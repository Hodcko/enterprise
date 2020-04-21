package com.github.hodcko.multy.dao;


import com.github.hodcko.multy.dao.impl.DaoGetIdByEmailDefault;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoGetIdTest {
    final DaoGetIdByEmail daoGetIdByEmail = DaoGetIdByEmailDefault.getInstance();




    @Test
    void createCursTest() {
        int studentId = daoGetIdByEmail.getId("hodckoq@gmail.com", "student");
        int teacherId = daoGetIdByEmail.getId("hodckoq@gmail.com", "teacher");
        assertEquals(1, studentId);
        assertEquals(2, teacherId);

    }

}
