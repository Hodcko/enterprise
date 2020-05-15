package com.github.hodcko.multy.web;

import com.github.hodcko.multy.dao.DaoAuthUser;
import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.dao.impl.DaoAuthUserDefault;
import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.dao.impl.DaoStudentDefault;
import com.github.hodcko.multy.dao.impl.DaoTeacherDefault;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.*;
import org.hibernate.Session;

public class test {
    public static void main(String[] args) {
        final DaoStudent daoStudent = DaoStudentDefault.getInstance();
        final DaoTeacher daoTeacher = DaoTeacherDefault.getInstance();
        final DaoCurs daoCurs = DaoCursDefault.getInstance();
        final DaoAuthUser daoAuthUser = DaoAuthUserDefault.getInstance();

//        AuthUser authUser = daoAuthUser.getAuthUser("Alex", "Hodcko31");
//        AuthUser authUser2 = daoAuthUser.getAuthUser("Alex", "Hodcko31");
//        AuthUser authUser3 = daoAuthUser.getAuthUser("Alex", "Hodcko31");
//        AuthUser authUser4 = daoAuthUser.getAuthUser("Alex", "Hodcko31");

//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            AuthUser authUser = session.get(AuthUser.class, 33);
//            session.getTransaction().commit();
//        }
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            AuthUser authUser2 = session.get(AuthUser.class, 33);
//            session.getTransaction().commit();
//        }
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            AuthUser authUser3 = session.find(AuthUser.class, 33);
//            session.getTransaction().commit();
//        }
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            AuthUser authUser4 = session.find(AuthUser.class, 33);
//            session.getTransaction().commit();
//        }




//
//        Curs curs = daoCurs.getCurs(1);
//        Curs curs2 = daoCurs.getCurs(1);
//        Curs curs3 = daoCurs.getCurs(1);
//        Curs curs4 = daoCurs.getCurs(1);




//        Student student = daoStudent.getStudent(216);
//        Student student2 = daoStudent.getStudent(216);
//        Student student3 = daoStudent.getStudent(216);
//        Student student4 = daoStudent.getStudent(216);

        Teacher teacher = daoTeacher.getTeacher(31);
        Teacher teacher2 = daoTeacher.getTeacher(31);
        Teacher teacher3 = daoTeacher.getTeacher(31);
        Teacher teacher4 = daoTeacher.getTeacher(31);





    }

}
