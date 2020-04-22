package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceStudent;
import com.github.hodcko.multy.service.ServiceTeacher;


public class ServiceRegistrationDefault implements com.github.hodcko.multy.service.ServiceRegistration {

    private static volatile com.github.hodcko.multy.service.ServiceRegistration instance;

    ServiceStudent serviceStudent = ServiceStudentDefault.getInstance();
    ServiceTeacher serviceTeacher = ServiceTeacherDefault.getInstance();
    ServiceCurs serviceCurs = ServiceCursDefault.getInstance();

    public static com.github.hodcko.multy.service.ServiceRegistration getInstance(){
        com.github.hodcko.multy.service.ServiceRegistration localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.service.ServiceRegistration.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceRegistrationDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean registration(String name, String secondName, String email, int age, String userType, String langType) {
        if(userType.equals("student")){
            serviceStudent.saveStudent(name, secondName, email, age, serviceCurs.getCurs_id(langType));
            return true;
        }else if(userType.equals("teacher")){
            serviceTeacher.saveTeacher(name, secondName, email, serviceCurs.getCurs_id(langType));
            return true;
        }else{
            return false;
        }
    }
}