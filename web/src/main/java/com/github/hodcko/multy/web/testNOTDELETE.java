package com.github.hodcko.multy.web;


import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.impl.DaoCursDefault;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.model.Student;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;


public class testNOTDELETE {
    public static void main(String[] args) {

        // One to many
//        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
//        Student student = new Student("Jack", "Dani1els6", "jack@mail.ru", 31, curs.getId());
//
//        curs.getStudentList().add(student);
//
//        System.out.println("student added");
//
//        Session session = SFUtil.getSession();
//        session.beginTransaction();
//        session.save(curs);
//        session.getTransaction().commit();
//
//        Session session1 = SFUtil.getSession();
//        session1.beginTransaction();
//        Curs curs1 = session1.get(Curs.class, 1);
//        System.out.println("curs getted");
//        System.out.println(curs.getStudentList());
//
//        student = curs1.getStudentList().iterator().next();
//        System.out.println("student getted");
//
//        System.out.println(student.getCurs().getStudentList());
//
//        curs1.getStudentList().remove(student);
//        session1.getTransaction().commit();
//        Employee employee1 = new Employee(null, "Yulij", "Slabko", null);
//        Employee employee2 = new Employee(null, "Sergey", "Kruk", null);
//        final ArrayList<Employee> employees = new ArrayList<>();
//        employees.add(employee1);
//        employees.add(employee2);

        // many to many
//        Student student = new Student("Jack", "Dani1els6", "jack@mail.ru", 31, 1);
//        ArrayList<Student> students = new ArrayList<>();
//        students.add(student);
//
//        Curs curs = new Curs(1, "Java", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
//        Curs curs2 = new Curs(2, "PHP", LocalDate.of(2020, 10, 10), LocalDate.of(2020, 12, 12));
//        ArrayList<Curs> cursArrayList = new ArrayList<>();
//        cursArrayList.add(curs);
//        cursArrayList.add(curs2);
//
//        student.getCurses().addAll(cursArrayList);
//
//        curs.getStudents().addAll(students);
//        curs2.getStudents().addAll(students);
//
//        Session session = SFUtil.getSession();
//        session.beginTransaction();
//        session.save(student);
//
//        session.getTransaction().commit();
//
//        session.beginTransaction();
//        Student studentTest = session.find(Student.class, student.getId());
//
//        Curs testCurs = session.find(Curs.class, curs.getId());
//        Curs testCurs2 = session.find(Curs.class, curs2.getId());
//
//        System.out.println(studentTest.getCurses());
//
//
//        System.out.println(testCurs.getStudents());
//        System.out.println(testCurs2.getStudents());
//
//        studentTest.getCurses().remove(0);
//        studentTest.getCurses().remove(0);
//
//        testCurs.getStudents().remove(0);
//        testCurs2.getStudents().remove(0);
//
//        session.getTransaction().commit();

        DaoCurs daoCurs = DaoCursDefault.getInstance();
        System.out.println(daoCurs.getMyStudents(1,2));








    }
}
