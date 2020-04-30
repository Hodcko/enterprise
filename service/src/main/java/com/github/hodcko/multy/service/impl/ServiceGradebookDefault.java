package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoGradebook;
import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;

public class ServiceGradebookDefault implements com.github.hodcko.multy.service.ServiceGradebook {
    private DaoGradebook daoGradebook = DaoGradebookDefault.getInstance();

    private static volatile com.github.hodcko.multy.service.ServiceGradebook instance;

    public static com.github.hodcko.multy.service.ServiceGradebook getInstance(){
        com.github.hodcko.multy.service.ServiceGradebook localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.service.ServiceGradebook.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new ServiceGradebookDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int addStudentToGradebook(int studentId){
        return daoGradebook.addStudentToGradebook(studentId);
    }

    @Override
    public int addGrade(int studentId){
        return daoGradebook.addGrade(studentId);
    }

    @Override
    public int getGrade(int studentId){
        return daoGradebook.getGrade(studentId);
    }

    @Override
    public boolean isExist(int studentId){
        return daoGradebook.isExist(studentId);
    }

    @Override
    public boolean deleteStudentFromGradebook(int studentId) {
        return daoGradebook.deleteStudentFromGradebook(studentId);
    }

    @Override
    public int checkTest(int studentId, String first, String second, String third, String fourth, String fifth){
        if(first.equalsIgnoreCase("java")){
            addGrade(studentId);
        }
        if(second.equalsIgnoreCase("programming")){
            addGrade(studentId);
        }
        if(third.equalsIgnoreCase("interface")){
            addGrade(studentId);
        }
        if(fourth.equalsIgnoreCase("4")){
            addGrade(studentId);
        }
        if(fifth.equalsIgnoreCase("8")){
            addGrade(studentId);
        }
        return getGrade(studentId);
    }
}
