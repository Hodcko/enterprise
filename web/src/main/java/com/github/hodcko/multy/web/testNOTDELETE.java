package com.github.hodcko.multy.web;


import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.Student;
import org.hibernate.Session;


public class testNOTDELETE {
    public static void main(String[] args) {
//
//        try (Session session = SFUtil.getSession()) {
//            session.beginTransaction();
//            Curs curs = session.get(Curs.class, 2);
//            System.out.println(curs.getStudents().size());
//            session.getTransaction().commit();
//        }
//        SFUtil.closeSessionFactory();

        DaoCurs daoCurs = DaoCursDefault.getInstance();
        System.out.println(daoCurs.getClassmates(1));





    }
}
