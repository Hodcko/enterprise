package com.github.hodcko.multy.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "teacher")
public class Teacher {
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
    private Integer curs_id;


    public Teacher(){
    }

    public Teacher(String name, String secondName, String email, Integer curs_id) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.curs_id = curs_id;
    }

    public Teacher(Integer id, String name, String secondName, String email, Integer curs_id) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.curs_id = curs_id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private AuthUser authUser;

    public Integer getId() {
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

    public Integer getCurs_id() {
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
        return Objects.equals(id, teacher.id) &&
                Objects.equals(name, teacher.name) &&
                Objects.equals(secondName, teacher.secondName) &&
                Objects.equals(email, teacher.email) &&
                Objects.equals(curs_id, teacher.curs_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, email, curs_id);
    }
}
