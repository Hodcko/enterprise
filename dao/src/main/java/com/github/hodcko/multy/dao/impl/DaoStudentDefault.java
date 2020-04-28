package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.DaoStudent;
import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Student;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DaoStudentDefault implements DaoStudent {

    private static volatile DaoStudent instance;
    private static final Logger log = LoggerFactory.getLogger(DaoStudentDefault.class);

    public static DaoStudent getInstance(){
        DaoStudent localInstance = instance;
        if(localInstance == null){
            synchronized (DaoStudent.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoStudentDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Student saveStudent(String name, String second_name, String email, int age, int curs_id) {
        Student student = new Student(name, second_name, email, age, curs_id);
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
            log.info("create student: name {} second name  {} email {}", name, second_name, email);
            return student;
        }catch (HibernateError e){
            log.error("fail to create student: name {} second name  {} email {}", name, second_name, email, e);
        }
        return null;
    }

    @Override
    public Student getStudent(int id){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            log.info("get student: name {} second name  {} email {}", student.getName(), student.getSecondName(), student.getEmail());
            return student;
        }catch (HibernateError | NullPointerException e){
            log.error("fail to get student with id{}", id, e);
        }
        return null;
    }

    @Override
    public boolean deleteStudent(String email){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Student student = session.createQuery("select s from Student s where email = :mail", Student.class)
                    .setParameter("mail", email).getSingleResult();
            session.delete(student);
            session.getTransaction().commit();
            log.info("deleted student with email {}", email);
            return true;
        }catch (HibernateError e){
            log.error("fail to deleted student with email {}", email);
        }
        return false;
    }
}
