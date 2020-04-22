package com.github.hodcko.multy.model;

public class test {
    public static void main(String[] args) {
        System.out.println(UserType.STUDENT);
        String student = "student";
        System.out.println(UserType.valueOf(student.toUpperCase()));

    }
}
