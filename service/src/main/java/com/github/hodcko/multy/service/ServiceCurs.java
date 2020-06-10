package com.github.hodcko.multy.service;

import com.github.hodcko.multy.dao.entity.GroupDTO;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface ServiceCurs {

    Curs createCurs(String name, LocalDate start, LocalDate end);

    int getCursId(String langType);

    Curs getCurs(int cursId);

    List<GroupDTO> getMyStudents(int cursId, int numPage);

    int countOfStudents(int cursId);

    List<Student> getClassmates(int cursId);

    boolean deleteCurs(int cursId);

    boolean inviteStudentOnCurs(int studentId, int cursId);

    List<Teacher> getColleagues(int cursId);

}
