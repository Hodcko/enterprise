package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.DTOGroup;
import com.github.hodcko.multy.model.Student;

import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface DaoCurs {

    Curs createCurs(String name, LocalDate start, LocalDate end);

    Curs getCurs(int curs_id);

    boolean deleteCurs(int curs_id);

    List<DTOGroup> getMyStudents(int curs_id, int numPage);

    int countOfStudents(int curs_id);

    List<Student> getClassmates(int curs_id);
}
