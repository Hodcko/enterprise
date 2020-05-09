package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Gradebook;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;


public class DaoGradebookDefault implements com.github.hodcko.multy.dao.DaoGradebook {

    private static volatile com.github.hodcko.multy.dao.DaoGradebook instance;
    private static final Logger log = LoggerFactory.getLogger(DaoGradebookDefault.class);



    public static com.github.hodcko.multy.dao.DaoGradebook getInstance(){
        com.github.hodcko.multy.dao.DaoGradebook localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.dao.DaoGradebook.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoGradebookDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int addGrade(int studentId, int cursId) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Gradebook gradebook = session.createQuery("select g from Gradebook g where studentId = :id and cursId = :cursId",
                    Gradebook.class).setParameter("id", studentId).setParameter("cursId", cursId).getSingleResult();
            gradebook.setGrade(gradebook.getGrade() + 1);
            session.getTransaction().commit();
            log.info("increment grade by student with id {} and cursId {}", studentId, cursId);
            return studentId;
        }catch (HibernateError e){
            log.error("fail to increment grade by student with id {} and cursId {}", studentId, cursId, e);
        }
        return 0;
    }

    @Override
    public int addStudentToGradebook(int studentId, int cursId){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Gradebook gradebook = new Gradebook(studentId, cursId, 0);
            session.save(gradebook);
            session.getTransaction().commit();
            log.info("added to gradeBook student with id {} and cursId {}", studentId, cursId);
            return studentId;
        }catch (HibernateError e){
            log.error("fail to add to gradeBook student with id {} and cursId {}", studentId, cursId, e);
        }
        return 0;
    }

    @Override
    public int getGrade(int studentId, int cursId){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            int grade = (int) session.createQuery("select g.grade from Gradebook g where g.studentId = :id" +
                    " and g.cursId = :cursId")
                    .setParameter("id", studentId).setParameter("cursId", cursId).getSingleResult();
            session.getTransaction().commit();
            log.info("student with id {} and cursId {} take grade {}", studentId, cursId, grade);
            return grade;
        }catch (HibernateError e){
            log.error("fail to get grade from student with id {} and cursId {}", studentId, cursId, e);
        }
        return 0;
    }

    @Override
    public boolean deleteStudentFromGradebook(int studentId, int cursId){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Gradebook gradebook = session.createQuery("select g from Gradebook g where studentId = :id and cursId = :cursId",
                    Gradebook.class).setParameter("id", studentId).setParameter("cursId", cursId).getSingleResult();
            session.delete(gradebook);
            session.getTransaction().commit();
            log.info("Student with id {} and cursId {} deleted from GradeBook", studentId, cursId);
            return true;
        }catch (HibernateError e){
            log.error(" fail to delete Student with id {} and cursId {} from GradeBook", studentId, cursId, e);
        }
        return false;
    }

    @Override
    public boolean isExist(int studentId){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            List ids = session.createQuery("select g.studentId from Gradebook g where g.studentId = :id")
                    .setParameter("id", studentId).getResultList();
            if (ids.size() > 0) {
                log.info("student with id {} is already existed", studentId);
                return true;
            }
        }catch (HibernateException | NoResultException e){
            log.error("fail to check student with id {}", studentId, e);
            return false;
        }
        return false;
    }
}
