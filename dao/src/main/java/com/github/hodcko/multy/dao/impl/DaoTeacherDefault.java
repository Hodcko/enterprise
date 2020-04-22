package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.dao.utils.MysqlDataBase;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Teacher saveTeacher(String name, String second_name, String email,  int curs_id) {
        Teacher teacher = new Teacher(name, second_name, email, curs_id);
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
        }catch (HibernateError e){
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
}
