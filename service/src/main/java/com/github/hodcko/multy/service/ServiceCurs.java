package com.github.hodcko.multy.service;

import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.DTOGroup;
import com.github.hodcko.multy.model.Student;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ServiceCurs {

    Curs createCurs(String name, LocalDate start, LocalDate end);

    int getCurs_id(String langType);

    Curs getCurs(int curs_id);

    List<DTOGroup> getMyStudents(int curs_id, int numPage);

    int countOfStudents(int curs_id);

    List<Student> getClassmates(int curs_id);

}
