package com.github.hodcko.multy.model;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "student")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student extends User{
    @Column
    private Integer age;

   public Student(){
   }

    public Student(String name, String secondName, String email, Integer age, Integer cursId) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
        this.cursId = cursId;
    }

    public Student(int id, String name, String secondName, String email, Integer age, Integer cursId) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
        this.cursId = cursId;
    }

    public Student(String name, String secondName, String email, Integer age, Curs curs) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.age = age;
        this.curs = curs;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private AuthUser authUser;

    @ManyToOne
    @JoinColumn(name = "curs_id", insertable = false, updatable = false)
    private Curs curs;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "student_curs", joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "curs_id")}
    )
    private List<Curs> curses = new ArrayList<>();


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Curs getCurs() {
        return curs;
    }

    public void setCurs(Curs curs) {
        this.curs = curs;
    }


    public List<Curs> getCurses() {
        return curses;
    }

    public void setCurses(List<Curs> curses) {
        this.curses = curses;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", curs_id=" + cursId +
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
