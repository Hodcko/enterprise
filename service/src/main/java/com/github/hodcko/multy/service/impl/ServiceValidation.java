package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.service.IServiceValidation;


public class ServiceValidation implements IServiceValidation {
    String mailPattern = ("[a-zA-Z0-9]+@[a-zA-Z0-9.]+");
    String namePattern = ("[a-zA-Zа-яА-Я]+");
    String agePattern = ("[0-9]{2}");

    private static volatile IServiceValidation instance;

    public static IServiceValidation getInstance(){
        IServiceValidation localInstance = instance;
        if(localInstance == null){
            synchronized (IServiceValidation.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceValidation();
                }
            }
        }
        return localInstance;
    }

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
    @Override
    public boolean validationTeacher(String name, String secondName, String email) {
        String mailPattern = ("[a-zA-Z0-9]+@[a-zA-Z0-9.]+");
        String namePattern = ("[a-zA-Zа-яА-Я]+");

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
