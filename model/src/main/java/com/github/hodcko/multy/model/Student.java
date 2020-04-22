package com.github.hodcko.multy.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "student")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column (name = "second_name")
    private String secondName;
    @Column
    private String email;
    @Column
    private Integer age;
    @Column
    private Integer curs_id;


   public Student(){

   }

    public Student(String name, String secondName, String email, Integer age, Integer curs_id) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
        this.curs_id = curs_id;
    }

    public Student(int id, String name, String secondName, String email, Integer age, Integer curs_id) {
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

    public Integer getAge() {
        return age;
    }

    public Integer getCurs_id() {
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
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(secondName, student.secondName) &&
                Objects.equals(email, student.email) &&
                Objects.equals(age, student.age) &&
                Objects.equals(curs_id, student.curs_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, email, age, curs_id);
    }
}
