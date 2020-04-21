package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.service.impl.ServiceCursDefault;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceCursDefaultTest {

    @Mock
    private static DaoCurs daoCurs;

    @InjectMocks
    private static ServiceCursDefault iServiceCursDefault;


    @Test
    void createCursTest(){
        Curs curs = new Curs(1, "Java", new Date(2020, 4, 10), new Date(2020, 4, 10));
        when(daoCurs.createCurs(curs.getName(), curs.getStart(), curs.getEnd())).thenReturn(curs);
        Curs cursTest = iServiceCursDefault.createCurs("Java", new Date(2020, 4, 10), new Date(2020, 4, 10));
        assertEquals(curs, cursTest);
    }

    @Test
    void getCursIdTest(){
        int cursId = iServiceCursDefault.getCurs_id("java");
        assertEquals(cursId, 1);
    }

    @Test
    void getCursTest(){
        Curs curs = new Curs(1, "Java", new Date(2020, 4, 10), new Date(2020, 4, 10));
        when(daoCurs.getCurs(1)).thenReturn(curs);
        Curs cursTest = iServiceCursDefault.getCurs(1);
        assertEquals(curs, cursTest);
    }





}
