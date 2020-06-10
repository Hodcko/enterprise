package com.github.hodcko.multy.dao.entity;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gradebook")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GradebookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column (name = "student_id")
    private Integer studentId;
    @Column (name = "curs_id")
    private Integer cursId;
    @Column
    private Integer grade;

    public GradebookEntity() {
    }

    public GradebookEntity(Integer studentId, Integer cursId, Integer grade){
        this.studentId = studentId;
        this.cursId = cursId;
        this.grade = grade;
    }

    public GradebookEntity(Integer id, Integer studentId, Integer cursId, Integer grade){
        this.id = id;
        this.studentId = studentId;
        this.cursId = cursId;
        this.grade = grade;
    }

    public Integer getCursId() {
        return cursId;
    }

    public void setCursId(Integer cursId) {
        this.cursId = cursId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradebookEntity gradebook = (GradebookEntity) o;
        return Objects.equals(id, gradebook.id) &&
                Objects.equals(studentId, gradebook.studentId) &&
                Objects.equals(cursId, gradebook.cursId) &&
                Objects.equals(grade, gradebook.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, cursId, grade);
    }

}
