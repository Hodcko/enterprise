package com.github.hodcko.multy.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "student")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column (name = "second_name")
    private String secondName;
    @Column
    private String email;
    @Column
    private int age;
    @Column
    private int curs_id;


   public Student(){

   }

    public Student(String name, String secondName, String email, int age, int curs_id) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
        this.curs_id = curs_id;
    }

    public Student(int id, String name, String secondName, String email, int age, int curs_id) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
        this.curs_id = curs_id;
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

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public int getCurs_id() {
        return curs_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", curs_id=" + curs_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                age == student.age &&
                curs_id == student.curs_id &&
                Objects.equals(name, student.name) &&
                Objects.equals(secondName, student.secondName) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, email, age, curs_id);
    }
}
