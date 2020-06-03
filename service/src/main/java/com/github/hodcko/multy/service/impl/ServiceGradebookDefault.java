package com.github.hodcko.multy.service.impl;


import com.github.hodcko.multy.dao.DaoGradebook;
import org.springframework.transaction.annotation.Transactional;

public class ServiceGradebookDefault implements com.github.hodcko.multy.service.ServiceGradebook {
    private final DaoGradebook daoGradebook;

    public ServiceGradebookDefault(DaoGradebook daoGradebook) {
        this.daoGradebook = daoGradebook;
    }

    @Transactional
    @Override
    public int addStudentToGradebook(int studentId, int cursId){
        return daoGradebook.addStudentToGradebook(studentId, cursId);
    }

    @Transactional
    @Override
    public int addGrade(int studentId, int cursId){
        return daoGradebook.addGrade(studentId, cursId);
    }

    @Transactional
    @Override
    public int getGrade(int studentId, int cursId){
        return daoGradebook.getGrade(studentId, cursId);
    }

    @Transactional
    @Override
    public boolean isExist(int studentId){
        return daoGradebook.isExist(studentId);
    }

    @Transactional
    @Override
    public boolean deleteStudentFromGradebook(int studentId, int cursId) {
        return daoGradebook.deleteStudentFromGradebook(studentId, cursId);
    }

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
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
