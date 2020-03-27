package com.hodcko;



import java.util.List;

public interface Service {
    void saveStudent(Student student);

    List<Student> getStudentsList();

}
