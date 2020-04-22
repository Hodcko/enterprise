package com.github.hodcko.multy.model;


import java.util.Objects;


public class DTOGroup {

    private String name;

    private String secondName;

    private String email;

    private Integer grade;

    public DTOGroup(String name, String secondName, String email, Integer grade) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.grade = grade;
    }

    public DTOGroup() {
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

    public void setGrade(Integer grade) {
        this.grade = grade;
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

    public Integer getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "DTOGroup{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", grade=" + grade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTOGroup dtoGroup = (DTOGroup) o;
        return Objects.equals(name, dtoGroup.name) &&
                Objects.equals(secondName, dtoGroup.secondName) &&
                Objects.equals(email, dtoGroup.email) &&
                Objects.equals(grade, dtoGroup.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName, email, grade);
    }
}
