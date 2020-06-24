package com.github.hodcko.multy.service.impl;


import org.springframework.transaction.annotation.Transactional;

public class ServiceValidationDefault implements com.github.hodcko.multy.service.ServiceValidation {
    private String mailPattern = ("[a-zA-Z0-9]+@[a-zA-Z0-9.]+");
    private String namePattern = ("[a-zA-Zа-яА-Я]+");
    private String agePattern = ("[0-9]{2}");


    @Transactional
    @Override
    public boolean validationStudent(String name, String secondName, String email, int age) {
        if(name.matches(namePattern)){
            if(secondName.matches(namePattern)){
                if(email.matches(mailPattern)){
                    if(String.valueOf(age).matches(agePattern)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Transactional
    @Override
    public boolean validationTeacher(String name, String secondName, String email) {
        if(name.matches(namePattern)){
            if(secondName.matches(namePattern)){
                if(email.matches(mailPattern)){
                    return true;
                }
            }
        }
        return false;
    }
}
