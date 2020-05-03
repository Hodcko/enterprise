package com.github.hodcko.multy.service.impl;

import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.DaoGradebook;
import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import com.github.hodcko.multy.service.ServiceCurs;

public class ServiceGradebookDefault implements com.github.hodcko.multy.service.ServiceGradebook {
    private DaoGradebook daoGradebook = DaoGradebookDefault.getInstance();
    private ServiceCurs serviceCurs = ServiceCursDefault.getInstance();


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
    public int addStudentToGradebook(int studentId, int cursId){
        return daoGradebook.addStudentToGradebook(studentId, cursId);
    }

    @Override
    public int addGrade(int studentId, int cursId){
        return daoGradebook.addGrade(studentId, cursId);
    }

    @Override
    public int getGrade(int studentId, int cursId){
        return daoGradebook.getGrade(studentId, cursId);
    }

    @Override
    public boolean isExist(int studentId){
        return daoGradebook.isExist(studentId);
    }

    @Override
    public boolean deleteStudentFromGradebook(int studentId, int cursId) {
        return daoGradebook.deleteStudentFromGradebook(studentId, cursId);
    }

    @Override
    public int checkTest(int studentId, int cursId, String first, String second, String third, String fourth, String fifth){
        if(cursId == 1){
            return checkTestJava(studentId, cursId, first, second, third, fourth, fifth);
        }
        if(cursId == 2){
            return checkTestPHP(studentId, cursId, first, second, third, fourth, fifth);
        }
        if(cursId == 3){
            return checkTestCPlusPlus(studentId, cursId, first, second, third, fourth, fifth);
        }
        return 0;
    }

    @Override
    public int checkTestJava(int studentId, int cursId, String first, String second, String third, String fourth, String fifth){
        if(first.equalsIgnoreCase("java")){
            addGrade(studentId, cursId);
        }
        if(second.equalsIgnoreCase("programming")){
            addGrade(studentId, cursId);
        }
        if(third.equalsIgnoreCase("interface")){
            addGrade(studentId, cursId);
        }
        if(fourth.equalsIgnoreCase("4")){
            addGrade(studentId, cursId);
        }
        if(fifth.equalsIgnoreCase("8")){
            addGrade(studentId, cursId);
        }
        return getGrade(studentId, cursId);
    }

    @Override
    public int checkTestCPlusPlus(int studentId, int cursId, String first, String second, String third, String fourth, String fifth){
        if(first.equalsIgnoreCase("C")){
            addGrade(studentId, cursId);
        }
        if(second.equalsIgnoreCase("bool")){
            addGrade(studentId, cursId);
        }
        if(third.equalsIgnoreCase("yes")){
            addGrade(studentId, cursId);
        }
        if(fourth.equalsIgnoreCase("parametric")){
            addGrade(studentId, cursId);
        }
        if(fifth.equalsIgnoreCase("6")){
            addGrade(studentId, cursId);
        }
        return getGrade(studentId, cursId);
    }

    @Override
    public int checkTestPHP(int studentId, int cursId, String first, String second, String third, String fourth, String fifth){
        if(first.equalsIgnoreCase("dynamic")){
            addGrade(studentId, cursId);
        }
        if(second.equalsIgnoreCase("4")){
            addGrade(studentId, cursId);
        }
        if(third.equalsIgnoreCase("$")){
            addGrade(studentId, cursId);
        }
        if(fourth.equalsIgnoreCase("5")){
            addGrade(studentId, cursId);
        }
        if(fifth.equalsIgnoreCase("::")){
            addGrade(studentId, cursId);
        }
        return getGrade(studentId, cursId);
    }




}
