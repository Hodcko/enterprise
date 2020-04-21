package com.github.hodcko.multy.web;

import com.github.hodcko.multy.dao.DaoAuthUser;
import com.github.hodcko.multy.dao.impl.DaoAuthUserUserDefault;

public class test {
    public static void main(String[] args) {

        DaoAuthUser daoAuthUser = DaoAuthUserUserDefault.getInstance();
//        System.out.println(iDaoAuth.getByLogin("Hodcko4"));
//        System.out.println(iDaoAuth.saveAuthUser(24, "John", "Snow24", "student"));

        System.out.println(daoAuthUser.passwordGenerate("winter@gmail.com", "student"));
        System.out.println(daoAuthUser.passwordGenerate("hiber@mail.ru", "teacher"));


    }
}
