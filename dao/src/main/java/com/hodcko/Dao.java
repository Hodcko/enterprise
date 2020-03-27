package com.hodcko;

import java.util.List;

public interface Dao {


    void saveStudent(Student student);

    List<Student> getStudentsList();


}
