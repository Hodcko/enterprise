package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.model.Curs;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoCursTest {

    final DaoCurs daoCurs = DaoCursDefault.getInstance();

    @Test
    void createCursTest() {
        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        Curs testCurs = daoCurs.createCurs("Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        daoCurs.deleteCurs(testCurs.getId());
        assertEquals(curs, testCurs);
    }

    @Test
    void getCursTest() {
        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        Curs testCurs = daoCurs.createCurs("Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
        assertEquals(curs, daoCurs.getCurs(curs.getId()));
        daoCurs.deleteCurs(testCurs.getId());

    }



}
