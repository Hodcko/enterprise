package com.github.hodcko.multy.model;


import java.util.Objects;


public class Student extends User{
    private Integer age;

    public Student() {
    }



    public Student(String name, String secondName, String email, Integer age) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
    }


    public Student(int id, String name, String secondName, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", curs_id=" +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
