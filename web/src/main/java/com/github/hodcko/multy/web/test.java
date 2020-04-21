package com.github.hodcko.multy.web;

import com.github.hodcko.multy.dao.IDaoAuth;
import com.github.hodcko.multy.dao.IDaoStudent;
import com.github.hodcko.multy.dao.impl.DaoStudentManager;
import com.github.hodcko.multy.dao.impl.DaoUserAuth;
import com.github.hodcko.multy.dao.utils.HibernateUtil;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.sql.*;

public class test {
    public static void main(String[] args) {

        IDaoAuth iDaoAuth = DaoUserAuth.getInstance();
//        System.out.println(iDaoAuth.getByLogin("Hodcko4"));
//        System.out.println(iDaoAuth.saveAuthUser(24, "John", "Snow24", "student"));

        System.out.println(iDaoAuth.passwordGenerate("winter@gmail.com", "student"));
        System.out.println(iDaoAuth.passwordGenerate("hiber@mail.ru", "teacher"));


    }
}
