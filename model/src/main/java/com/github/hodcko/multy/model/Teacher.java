package com.github.hodcko.multy.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "teacher")
public class Teacher {
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
    private int curs_id;


    public Teacher(){
    }

    public Teacher(String name, String secondName, String email, int curs_id) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.curs_id = curs_id;
    }

    public Teacher(int id, String name, String secondName, String email, int curs_id) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
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

    public int getCurs_id() {
        return curs_id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", curs_id=" + curs_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id &&
                curs_id == teacher.curs_id &&
                Objects.equals(name, teacher.name) &&
                Objects.equals(secondName, teacher.secondName) &&
                Objects.equals(email, teacher.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, email, curs_id);
    }
}
