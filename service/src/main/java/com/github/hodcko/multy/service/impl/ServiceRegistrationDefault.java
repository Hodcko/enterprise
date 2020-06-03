package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.ServiceCurs;
import com.github.hodcko.multy.service.ServiceStudent;
import com.github.hodcko.multy.service.ServiceTeacher;
import org.springframework.transaction.annotation.Transactional;


public class ServiceRegistrationDefault implements com.github.hodcko.multy.service.ServiceRegistration {

    private final  ServiceStudent serviceStudent;
    private final  ServiceTeacher serviceTeacher;
    private final  ServiceCurs serviceCurs;

    public ServiceRegistrationDefault(ServiceStudent serviceStudent, ServiceTeacher serviceTeacher, ServiceCurs serviceCurs) {
        this.serviceStudent = serviceStudent;
        this.serviceTeacher = serviceTeacher;
        this.serviceCurs = serviceCurs;
    }


    @Transactional
    @Override
    public boolean registration(String name, String secondName, String email, int age, UserType userType, String langType) {
        if(userType.equals(UserType.TEACHER)){
            serviceTeacher.saveTeacher(name, secondName, email, serviceCurs.getCursId(langType));
            return true;
        }else if(userType.equals(UserType.STUDENT)){
            serviceStudent.saveStudent(name, secondName, email, age);
            return true;
        }else{
            return false;
        }
    }
}
