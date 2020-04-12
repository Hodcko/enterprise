package com.github.hodcko.multy.service;

public interface IServiceValidation {

    boolean validationStudent(String name, String secondName, String email, int age);

    boolean validationTeacher(String name, String secondName, String email);
}
