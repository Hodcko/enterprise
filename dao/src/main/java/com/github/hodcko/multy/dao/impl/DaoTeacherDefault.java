package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;


public class DaoTeacherDefault implements DaoTeacher {

    private static final Logger log = LoggerFactory.getLogger(DaoTeacherDefault.class);
    private final SessionFactory factory;

    public DaoTeacherDefault(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public Teacher saveTeacher(String name, String secondName, String email,  int cursId) {
        Teacher teacher = new Teacher(name, secondName, email, cursId);
        try {
            Session session =  factory.getCurrentSession();
            session.saveOrUpdate(teacher);
            log.info("create teacher: name {} second name  {} email {}", name, secondName, email);
            return teacher;
        }catch (HibernateError e){
            log.error("fail to create teacher: name {} second name  {} email {}", name, secondName, email, e);
        }
        return null;
    }

    @Override
    public Teacher getTeacher(int id){
        try {
            Session session =  factory.getCurrentSession();
            Teacher teacher = session.get(Teacher.class, id);
            log.info("get teacher: name {} second name  {} email {}", teacher.getName(), teacher.getSecondName(), teacher.getEmail());
            return teacher;
        }catch (HibernateError | NullPointerException e){
            log.error("fail to get teacher with id{}", id, e);
        }
        return null;
    }

    @Override
    public boolean deleteTeacher(String email){
        try {
            Session session =  factory.getCurrentSession();
            Teacher teacher = session.createQuery("select s from Teacher s where email = :mail", Teacher.class)
                    .setParameter("mail", email).getSingleResult();
            session.delete(teacher);
            log.info("deleted teacher with email {}", email);
            return true;
        }catch (HibernateError e){
            log.error("fail to deleted teacher with email {}", email);
        }
        return false;
    }

    @Override
    public boolean isExist(String email, UserType userType) {
        try {
            Session session =  factory.getCurrentSession();
            String teacherEmail = (String) session.createQuery("select t.email from Teacher t where t.email = :mail")
                    .setParameter("mail", email).getSingleResult();
            if (email.equalsIgnoreCase(teacherEmail)) {
                log.info("teacher with email {} is already existed", email);
                return true;
            }
        } catch (HibernateException | NoResultException e) {
            log.error("fail to check teacher with email {}", email, e);
        }
        return false;
    }

    @Override
    public int getId(String email, UserType userType) {
        try {
            Session session =  factory.getCurrentSession();
            int id = (int) session.createQuery("select t.id from Teacher t where t.email = :email")
                    .setParameter("email", email).getSingleResult();
            log.info("return id {} of teacher with email {}", id, email);
            return id;
        } catch (HibernateError e) {
            log.error("fail to return id of teacher with email {}", email, e);
        }
        return 0;
    }

    @Override
    public String passwordGenerate(String email, UserType userType) {
        try {
            Session session =  factory.getCurrentSession();
            Teacher teacher = (Teacher) session.createQuery("from Teacher t where t.email = :email")
                    .setParameter("email", email).getSingleResult();
            log.info("teacher with email {} generate password {}", email, teacher.getSecondName() + teacher.getId());
            return teacher.getSecondName() + teacher.getId();
        } catch (HibernateError e) {
            log.error("fail to generate password for teacher with email {}", email, e);
        }
        return null;
    }
}
