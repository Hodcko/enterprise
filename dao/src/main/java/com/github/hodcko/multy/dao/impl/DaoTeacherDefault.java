package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.dao.utils.MysqlDataBase;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import com.github.hodcko.multy.model.UserType;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.sql.*;

public class DaoTeacherDefault implements DaoTeacher {
    private static volatile DaoTeacher instance;
    MysqlDataBase dataBase = new MysqlDataBase();
    private static final Logger log = LoggerFactory.getLogger(DaoTeacherDefault.class);


    public static DaoTeacher getInstance(){
        DaoTeacher localInstance = instance;
        if(localInstance == null){
            synchronized (DaoTeacher.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoTeacherDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Teacher saveTeacher(String name, String second_name, String email,  int cursId) {
        Teacher teacher = new Teacher(name, second_name, email, cursId);
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(teacher);
            session.getTransaction().commit();
            log.info("create teacher: name {} second name  {} email {}", name, second_name, email);
            return teacher;
        }catch (HibernateError e){
            log.error("fail to create teacher: name {} second name  {} email {}", name, second_name, email, e);
        }
        return null;
    }

    @Override
    public Teacher getTeacher(int id){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            session.getTransaction().commit();
            log.info("get teacher: name {} second name  {} email {}", teacher.getName(), teacher.getSecondName(), teacher.getEmail());
            return teacher;
        }catch (HibernateError | NullPointerException e){
            log.error("fail to get teacher with id{}", id, e);
        }
        return null;
    }

    @Override
    public boolean deleteTeacher(String email){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Teacher teacher = session.createQuery("select s from Teacher s where email = :mail", Teacher.class)
                    .setParameter("mail", email).getSingleResult();
            session.delete(teacher);
            session.getTransaction().commit();
            log.info("deleted teacher with email {}", email);
            return true;
        }catch (HibernateError e){
            log.error("fail to deleted teacher with email {}", email);
        }
        return false;
    }

    @Override
    public boolean isExist(String email, UserType userType) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            String teacherEmail = (String) session.createNativeQuery("select email from teacher where email = :mail")
                    .setParameter("mail", email).getSingleResult();
            session.getTransaction().commit();
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
        Teacher teacher;
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            teacher = session.createQuery("select s from Teacher s where email = :email", Teacher.class)
                    .setParameter("email", email).getSingleResult();
            session.getTransaction().commit();
            log.info("return id {} of teacher with email {}", teacher.getId(), email);
            return teacher.getId();
        } catch (HibernateError e) {
            log.error("fail to return id of teacher with email {}", email, e);
        }
        return 0;
    }

    @Override
    public String passwordGenerate(String email, UserType userType) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            String secondName = (String) session.createNativeQuery("select second_name from teacher where email = :mail")
                    .setParameter("mail", email).getSingleResult();
            int id = (Integer) session.createNativeQuery("select id from teacher where email = :mail")
                    .setParameter("mail", email).getSingleResult();
            session.getTransaction().commit();
            log.info("teacher with email {} generate password {}{}", email, secondName, id);
            return secondName + id;
        } catch (HibernateError e) {
            log.error("fail to generate password for teacher with email {}", email, e);
        }
        return null;
    }
}
