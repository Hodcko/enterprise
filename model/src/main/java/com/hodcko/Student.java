package com.hodcko;

import java.util.*;

public class Student{
    private int id;
    private String name;
    private String secondName;
    private String spec;

    Random random = new Random();

    public Student(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
        this.id = random.nextInt(1000) + 100;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", spec='" + spec + '\'' +
                '}';
    }
}
