package com.github.hodcko.multy.web;


import com.github.hodcko.multy.dao.DaoAuthUser;
import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.DaoGradebook;
import com.github.hodcko.multy.dao.impl.DaoAuthUserDefault;
import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.dao.impl.DaoGradebookDefault;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.UserType;
import com.github.hodcko.multy.service.*;
import com.github.hodcko.multy.service.impl.ServiceAuthUserDefault;
import com.github.hodcko.multy.service.impl.ServiceCursDefault;
import com.github.hodcko.multy.service.impl.ServiceGradebookDefault;
import com.github.hodcko.multy.service.impl.ServiceIsExistDefault;

import net.sf.ehcache.CacheManager;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class testNOTDELETE {
    public static void main(String[] args) {

//
//        Session session = SFUtil.getSession();
//        session.beginTransaction();
//        Curs curs1 =  session.get(Curs.class, 1);
//        Curs curs2 =  session.get(Curs.class, 2);
//        Curs curs3 =  session.get(Curs.class, 3);
//       // System.out.println(curs1.getStudents());
//        System.out.println(curs1.getStudentList());
//       // System.out.println(curs2.getStudents());
//        System.out.println(curs2.getStudentList());
//        //System.out.println(curs3.getStudents());
//        System.out.println(curs3.getStudentList());
//
//        Student student = session.get(Student.class, 1);
//        System.out.println("!!!!!!");
//        System.out.println(curs1.getStudentList().contains(student));
//        System.out.println();
//        session.getTransaction().commit();
//        session.close();

        ServiceGradebook serviceGradebook = ServiceGradebookDefault.getInstance();

//        serviceGradebook.addStudentToGradebook(1,1);
//        serviceGradebook.addStudentToGradebook(1,2);
//        serviceGradebook.addStudentToGradebook(1,3);

         ServiceAuthUser serviceAuthUser = ServiceAuthUserDefault.getInstance();
         serviceAuthUser.deleteAuthUser(212, UserType.STUDENT);











//        Student student = new Student("Yulij", "Slabko", "snow@gmail.com", 31, 1);
//        final ArrayList<Student> students = new ArrayList<>();
//        students.add(student);
//
//        final Curs curs1 = new Curs(1, "Java", LocalDate.now(), LocalDate.now());
//        final Curs curs2 = new Curs(2, "PHP", LocalDate.now(), LocalDate.now());
//
//        final ArrayList<Curs> cursArrayList = new ArrayList<>();
//        cursArrayList.add(curs1);
//        cursArrayList.add(curs2);
//
//        student.getCurses().addAll(cursArrayList);
//
//
//        curs1.getStudents().addAll(students);
//        curs2.getStudents().addAll(students);
//
//
//        Session em = SFUtil.getSession();
//        em.getTransaction().begin();
//        em.saveOrUpdate(student);
//        em.getTransaction().commit();
//        em.close();
//
//        SFUtil.closeSessionFactory();






        //  One to many
      //  Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
//        Student student = new Student("Jack", "Dani1els6", "jack@mail.ru", 31, curs.getId());
//
//        curs.getStudentList().add(student);

//        System.out.println("student added");
//
//        Session session = SFUtil.getSession();
//        session.beginTransaction();
//        session.save(curs);
//        session.getTransaction().commit();

//        Session session1 = SFUtil.getSession();
//        session1.beginTransaction();
//        Curs curs1 = session1.get(Curs.class, 1);
//        System.out.println("curs getted");
//        System.out.println(curs1.getStudentList());

       // student = curs1.getStudentList().iterator().next();
       // System.out.println("student getted");

       // System.out.println(student.getCurs().getStudentList());

       // curs1.getStudentList().remove(student);
       // session1.getTransaction().commit();


        // many to many
      //  Student student = new Student("Jack", "Dani1els7", "jack@mai1l.ru", 31, 1);
//        ArrayList<Student> students = new ArrayList<>();
//        students.add(student);

//        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
//        Curs curs2 = new Curs(2, "PHP", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
//        ArrayList<Curs> cursArrayList = new ArrayList<>();
//        cursArrayList.add(curs);
//        cursArrayList.add(curs2);

      //  student.getCurses().addAll(cursArrayList);

//        curs.getStudents().addAll(students);
//        curs2.getStudents().addAll(students);


//        session.save(student);
//
//        session.getTransaction().commit();
//
//        session.beginTransaction();

//        ServiceCurs serviceCurs = ServiceCursDefault.getInstance();





          //  SFUtil.closeSessionFactory();







//        Curs testCurs = session.find(Curs.class, curs.getId());
//        Curs testCurs2 = session.find(Curs.class, curs2.getId());
//
//        System.out.println(studentTest.getCurses());
//
//
//        System.out.println(testCurs.getStudents());
//        System.out.println(testCurs2.getStudents());

//        studentTest.getCurses().remove(0);
//        studentTest.getCurses().remove(0);

//        testCurs.getStudents().remove(0);
//        testCurs2.getStudents().remove(0);




//        session.close();












    }
}
