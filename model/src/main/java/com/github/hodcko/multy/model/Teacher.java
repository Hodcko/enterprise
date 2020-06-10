package com.github.hodcko.multy.model;


import java.util.Objects;


public class Teacher extends User{

    protected Integer cursId;

    public Teacher(){
    }


    public Teacher(Integer id, String name, String secondName, String email, Integer cursId) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.cursId = cursId;
    }




    public Integer getCursId() {
        return cursId;
    }

    public void setCursId(Integer cursId) {
        this.cursId = cursId;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", cursId=" + cursId +
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
                Objects.equals(cursId, teacher.cursId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, email, cursId);
    }
}
