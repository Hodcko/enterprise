package com.hodcko;

import java.util.ArrayList;
import java.util.List;

public class StudentManager implements Dao{
    private static volatile Dao instance;
    private  List<Student> studentsList;

    public StudentManager(){
        this.studentsList = new ArrayList<>();
    }

    public static Dao getInstance(){
        Dao localInstance = instance;
        if(localInstance == null){
            synchronized (Dao.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new StudentManager();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void saveStudent(Student student){
        studentsList.add(student);
    }

    @Override
    public List<Student> getStudentsList() {
        return studentsList;
    }

    @Override
    public void setSpec(String spec, Student student) {
        student.setSpec(spec);
    }

    @Override
    public String getSpec(Student student) {
        return student.getSpec();
    }
}
