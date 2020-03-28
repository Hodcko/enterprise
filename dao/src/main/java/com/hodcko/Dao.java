package com.hodcko;

import java.util.List;

public interface Dao {
    void setSpec(String spec, Student student);

    void saveStudent(Student student);

    List<Student> getStudentsList();


}
