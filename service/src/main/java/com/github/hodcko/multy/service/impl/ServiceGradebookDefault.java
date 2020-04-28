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
    public int addStudentToGradebook(int studetn_id){
        return daoGradebook.addStudentToGradebook(studetn_id);
    }

    @Override
    public int addGrade(int studetn_id){
        return daoGradebook.addGrade(studetn_id);
    }

    @Override
    public int getGrade(int studetn_id){
        return daoGradebook.getGrade(studetn_id);
    }

    @Override
    public boolean isExist(int student_id){
        return daoGradebook.isExist(student_id);
    }

    @Override
    public boolean deleteStudentFromGradebook(int student_id) {
        return daoGradebook.deleteStudentFromGradebook(student_id);
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
