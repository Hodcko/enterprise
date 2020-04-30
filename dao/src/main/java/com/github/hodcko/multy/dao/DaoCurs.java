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

    Curs getCurs(int cursId);

    boolean deleteCurs(int cursId);

    List<DTOGroup> getMyStudents(int cursId, int numPage);

    int countOfStudents(int cursId);

    List<Student> getClassmates(int cursId);
}
