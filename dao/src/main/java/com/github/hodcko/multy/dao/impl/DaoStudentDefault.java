package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;


public class DaoStudentDefault implements DaoStudent {

    private static final Logger log = LoggerFactory.getLogger(DaoStudentDefault.class);
    private final SessionFactory factory;

    public DaoStudentDefault(SessionFactory factory) {
        this.factory = factory;
    }


    @Override

    public Student saveStudent(String name, String second_name, String email, int age) {
        Student student = new Student(name, second_name, email, age);
        try {
            Session session =  factory.getCurrentSession();
            session.saveOrUpdate(student);;
            log.info("create student: name {} second name  {} email {}", name, second_name, email);
            return student;
        }catch (HibernateError e){
            log.error("fail to create student: name {} second name  {} email {}", name, second_name, email, e);
        }
        return null;
    }

    @Override
    public Student getStudent(int id){
        try {
            Session session =  factory.getCurrentSession();
            Student student = session.get(Student.class, id);
            log.info("get student: name {} second name  {} email {}", student.getName(), student.getSecondName(), student.getEmail());
            return student;
        }catch (HibernateError | NullPointerException e){
            log.error("fail to get student with id{}", id, e);
        }
        return null;
    }

    @Override
    public boolean deleteStudent(String email){
        try {
            Session session =  factory.getCurrentSession();
            Student student = session.createQuery("select s from Student s where email = :mail", Student.class)
                    .setParameter("mail", email).getSingleResult();
            session.delete(student);
            log.info("deleted student with email {}", email);
            return true;
        }catch (HibernateError e){
            log.error("fail to deleted student with email {}", email);
        }
        return false;
    }

    @Override
    public boolean isExist(String email, UserType userType) {
        try {
            Session session =  factory.getCurrentSession();
            String studentEmail = (String)session.createQuery("select s.email from Student s where s.email = :mail")
                    .setParameter("mail", email).getSingleResult();
            if (email.equalsIgnoreCase(studentEmail)) {
                log.info("student with email {} is already existed", email);
                return true;
            }
        } catch (HibernateException | NoResultException e) {
            log.error("fail to check student with email {}", email, e);
        }
        return false;
    }

    @Override
    public int getId(String email, UserType userType) {
        try {
            Session session =  factory.getCurrentSession();
            int id = (int) session.createQuery("select s.id from Student s where s.email = :email")
                    .setParameter("email", email).getSingleResult();
            log.info("return id {} of student with email {}", id, email);
            return id;
        } catch (HibernateError e) {
            log.error("fail to return id of student with email {}", email, e);
        }
        return 0;
    }

    @Override
    public String passwordGenerate(String email, UserType userType) {
        try {
            Session session =  factory.getCurrentSession();
            Student student = (Student)session.createQuery("from Student s where s.email = :email")
                    .setParameter("email", email).getSingleResult();
            log.info("student with email {} generate password {}", email, student.getSecondName() + student.getId());
            return student.getSecondName() + student.getId();
        } catch (HibernateError e) {
            log.error("fail to generate password for student with email {}", email, e);
        }
        return null;
    }
}
