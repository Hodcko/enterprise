package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.DTOGroup;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface DaoCurs {

    Curs createCurs(String name, LocalDate start, LocalDate end);

    Curs getCurs(int curs_id);

    boolean deleteCurs(int curs_id);

    List<DTOGroup> getMyStudents(int curs_id);
}
