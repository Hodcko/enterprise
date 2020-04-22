package com.github.hodcko.multy.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "gradebook")
public class Gradebook {

    @Id
    @Column (name = "student_id")
    private Integer studentId;
    @Column
    private Integer grade;

    public Gradebook() {
    }

    public Gradebook(Integer studentId, Integer grade){
        this.studentId = studentId;
        this.grade = grade;
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
        Gradebook gradebook = (Gradebook) o;
        return Objects.equals(studentId, gradebook.studentId) &&
                Objects.equals(grade, gradebook.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, grade);
    }

    @Override
    public String toString() {
        return "Gradebook{" +
                "studentId=" + studentId +
                ", grade=" + grade +
                '}';
    }
}
