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

    Curs getCurs(int cursId);

    List<DTOGroup> getMyStudents(int cursId, int numPage);

    int countOfStudents(int cursId);

    List<Student> getClassmates(int cursId);

    boolean deleteCurs(int cursId);

}
