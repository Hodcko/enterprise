package com.github.hodcko.multy.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "student_curs", joinColumns = {@JoinColumn(name = "student_id")},
//            inverseJoinColumns = {@JoinColumn(name = "curs_id")}
//    )
  //  private List<Curs> curses = new ArrayList<>();


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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCurs_id(Integer curs_id) {
        this.curs_id = curs_id;
    }

    public Integer getCurs_id() {
        return curs_id;
    }

    public Curs getCurs() {
        return curs;
    }

    public void setCurs(Curs curs) {
        this.curs = curs;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

//    public List<Curs> getCurses() {
//        return curses;
//    }
//
//    public void setCurses(List<Curs> curses) {
//        this.curses = curses;
//    }

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
