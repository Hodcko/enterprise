package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DaoGetIdByEmailDefault implements com.github.hodcko.multy.dao.DaoGetIdByEmail {

    private static volatile com.github.hodcko.multy.dao.DaoGetIdByEmail instance;
    private static final Logger log = LoggerFactory.getLogger(DaoGetIdByEmailDefault.class);



    public static com.github.hodcko.multy.dao.DaoGetIdByEmail getInstance(){
        com.github.hodcko.multy.dao.DaoGetIdByEmail localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.dao.DaoGetIdByEmail.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoGetIdByEmailDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int getId(String email, String userType) {
        if (userType.equalsIgnoreCase("student")) {
            Student student;
            try (Session session = SFUtil.getSession()) {
                session.beginTransaction();
                student = session.createQuery("select s from Student s where email = :email", Student.class)
                        .setParameter("email", email).getSingleResult();
                session.getTransaction().commit();
                log.info("return id {} of student with email {}", student.getId(), email);
                return student.getId();
            }catch (HibernateError e){
                log.error("fail to return id of student with email {}", email, e);
            }
            return 0;
        } else if (userType.equalsIgnoreCase("teacher")) {
            Teacher teacher;
            try (Session session = SFUtil.getSession()) {
                session.beginTransaction();
                teacher = session.createQuery("select s from Teacher s where email = :email", Teacher.class)
                        .setParameter("email", email).getSingleResult();
                session.getTransaction().commit();
                log.info("return id {} of teacher with email {}", teacher.getId(), email);
                return teacher.getId();
            }catch (HibernateError e){
                log.error("fail to return id of teacher with email {}", email, e);
            }
            return 0;
        }
        return 0;
    }
}
