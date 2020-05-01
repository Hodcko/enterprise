package com.github.hodcko.multy.dao;

import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.GroupDTO;
import com.github.hodcko.multy.model.Student;

import java.time.LocalDate;
import java.util.List;

public interface DaoCurs {

    Curs createCurs(String name, LocalDate start, LocalDate end);

    Curs getCurs(int cursId);

    boolean deleteCurs(int cursId);

    List<GroupDTO> getMyStudents(int cursId, int numPage);

    int countOfStudents(int cursId);

    List<Student> getClassmates(int cursId);
}
