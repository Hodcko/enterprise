package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.model.Curs;
import org.junit.jupiter.api.Test;


import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoCursTest {

    final DaoCurs daoCurs = DaoCursDefault.getInstance();

    @Test
    void createCursTest() {
        Curs curs = new Curs(1, "Java", new Date(2020, 10, 10), new Date(2020, 10, 10));
        daoCurs.createCurs("Java", new Date(2020, 10, 10), new Date(2020, 10, 10));
        assertEquals(curs, daoCurs.getCurs(1));
    }

    @Test
    void getCursTest() {
        Curs curs = new Curs(1, "Java", new Date(2020, 10, 10), new Date(2020, 10, 10));
        assertEquals(curs, daoCurs.getCurs(1));
    }



}
