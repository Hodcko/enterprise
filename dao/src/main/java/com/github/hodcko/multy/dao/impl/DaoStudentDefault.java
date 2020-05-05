package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.DaoStudent;
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
    public Student saveStudent(String name, String second_name, String email, int age) {
        Student student = new Student(name, second_name, email, age);
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

    @Override
    public boolean isExist(String email, UserType userType) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            String studentEmail = (String) session.createNativeQuery("select email from student where email = :mail")
                    .setParameter("mail", email).getSingleResult();
            session.getTransaction().commit();
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
        Student student;
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            student = session.createQuery("select s from Student s where email = :email", Student.class)
                    .setParameter("email", email).getSingleResult();
            session.getTransaction().commit();
            log.info("return id {} of student with email {}", student.getId(), email);
            return student.getId();
        } catch (HibernateError e) {
            log.error("fail to return id of student with email {}", email, e);
        }
        return 0;
    }

    @Override
    public String passwordGenerate(String email, UserType userType) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            String secondName = (String) session.createNativeQuery("select second_name from student where email = :mail")
                    .setParameter("mail", email).getSingleResult();
            int id = (Integer) session.createNativeQuery("select id from student where email = :mail")
                    .setParameter("mail", email).getSingleResult();
            session.getTransaction().commit();
            log.info("student with email {} generate password {}{}", email, secondName, id);
            return secondName + id;
        } catch (HibernateError e) {
            log.error("fail to generate password for student with email {}", email, e);
        }
        return null;
    }
}
