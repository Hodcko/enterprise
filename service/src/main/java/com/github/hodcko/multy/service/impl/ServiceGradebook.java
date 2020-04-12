package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.IDaoGradebook;
import com.github.hodcko.multy.service.IServiceGradebook;
import com.github.hodcko.multy.dao.impl.DaoGradebook;

public class ServiceGradebook implements IServiceGradebook {
    private IDaoGradebook iDaoGradebook = DaoGradebook.getInstance();

    private static volatile IServiceGradebook instance;

    public static IServiceGradebook getInstance(){
        IServiceGradebook localInstance = instance;
        if(localInstance == null){
            synchronized (IServiceGradebook.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceGradebook();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int addStudentToGradebook(int studetn_id){
        return iDaoGradebook.addStudentToGradebook(studetn_id);
    }

    @Override
    public int addGrade(int studetn_id){
        return iDaoGradebook.addGrade(studetn_id);
    }

    @Override
    public int getGrade(int studetn_id){
        return iDaoGradebook.getGrade(studetn_id);
    }

    @Override
    public int checkTest(int student_id, String first, String second, String third, String fourth, String fifth){
        if(first.equalsIgnoreCase("java")){
            addGrade(student_id);
        }
        if(second.equalsIgnoreCase("programming")){
            addGrade(student_id);
        }
        if(third.equalsIgnoreCase("interface")){
            addGrade(student_id);
        }
        if(fourth.equalsIgnoreCase("4")){
            addGrade(student_id);
        }
        if(fifth.equalsIgnoreCase("8")){
            addGrade(student_id);
        }
        return getGrade(student_id);
    }
}
