package com.github.hodcko.multy.service.impl;


public class ServiceValidationDefault implements com.github.hodcko.multy.service.ServiceValidation {
    private String mailPattern = ("[a-zA-Z0-9]+@[a-zA-Z0-9.]+");
    private String namePattern = ("[a-zA-Zа-яА-Я]+");
    private String agePattern = ("[0-9]{2}");

    private static volatile com.github.hodcko.multy.service.ServiceValidation instance;

    public static com.github.hodcko.multy.service.ServiceValidation getInstance(){
        com.github.hodcko.multy.service.ServiceValidation localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.service.ServiceValidation.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceValidationDefault();
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
