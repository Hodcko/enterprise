package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.service.IServiceCurs;
import com.github.hodcko.multy.service.IServiceRegistration;
import com.github.hodcko.multy.service.IServiceStudent;
import com.github.hodcko.multy.service.IServiceTeacher;


public class ServiceRegistration implements IServiceRegistration {
    private static volatile IServiceRegistration instance;

    IServiceStudent iServiceStudent = ServiceStudentManager.getInstance();
    IServiceTeacher iServiceTeacher = ServiceTeacherManager.getInstance();
    IServiceCurs iServiceCurs = ServiceCurs.getInstance();

    public static IServiceRegistration getInstance(){
        IServiceRegistration localInstance = instance;
        if(localInstance == null){
            synchronized (IServiceRegistration.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceRegistration();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean registration(String name, String secondName, String email, int age, String userType, String langType) {
        if(userType.equals("student")){
            iServiceStudent.saveStudent(name, secondName, email, age, iServiceCurs.getCurs_id(langType));
            return true;
        }else if(userType.equals("teacher")){
            iServiceTeacher.saveTeacher(name, secondName, email, iServiceCurs.getCurs_id(langType));
            return true;
        }else{
            return false;
        }
    }
}
