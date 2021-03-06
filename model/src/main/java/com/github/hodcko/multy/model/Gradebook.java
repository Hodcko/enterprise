package com.github.hodcko.multy.model;

import java.util.*;


public class Gradebook {

    private Integer id;

    private Integer studentId;

    private Integer cursId;

    private Integer grade;

    public Gradebook() {
    }


    public Gradebook(Integer id, Integer studentId, Integer cursId, Integer grade){
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
        Gradebook gradebook = (Gradebook) o;
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
