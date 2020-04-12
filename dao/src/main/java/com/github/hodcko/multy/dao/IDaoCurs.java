package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.DTOGroup;

import java.sql.Date;
import java.util.List;

public interface IDaoCurs {

    Curs createCurs(String name, Date start, Date end);

    Curs getCurs(int curs_id);

    List<DTOGroup> getMyStudents(int curs_id);
}
