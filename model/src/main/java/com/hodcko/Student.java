package com.hodcko;

import java.util.*;

public class Student{
    private int id;
    private String name;
    private String secondName;
    private String spec;

    
    Random random = new Random();

    public Student(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
        this.id = random.nextInt(200);
    }

    //1st branch merged pull request
    //its will be canceled

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", spec='" + spec + '\'' +
                '}';
    }


    //    public static void main(String[] args) {
//        Student student = new Student("Alex");
//        Student student1 = new Student("Mike");
//        student.addGrade2(Lessons.BIOLOGY, 10, 10);
//        student.addGrade2(Lessons.MATH, 5);
//        student.addGrade2(Lessons.ENGLISH, 10);
//        student.addGrade2(Lessons.BIOLOGY, 3, 10);
//
//        student1.addGrade2(Lessons.BIOLOGY, 5);
//        student1.addGrade2(Lessons.MATH, 6);
//        student1.addGrade2(Lessons.MATH, 2);
//        student1.addGrade2(Lessons.ENGLISH, 3);
//
//        System.out.println(student.gradeList2);
//        System.out.println(student1.gradeList2);
//
//        System.out.println(Student.journal2);
//    }

}
