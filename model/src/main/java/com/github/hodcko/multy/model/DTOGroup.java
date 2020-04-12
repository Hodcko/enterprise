package com.github.hodcko.multy.model;

public class DTOGroup {
    private String name;
    private String secondName;
    private String email;
    private int grade;

    public DTOGroup(String name, String secondName, String email, int grade) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
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

    public int getGrade() {
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
}
