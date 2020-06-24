package com.github.hodcko.multy.dao.impl;



import com.github.hodcko.multy.dao.entity.GradebookEntity;
import com.github.hodcko.multy.model.Gradebook;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.NoResultException;
import java.util.List;


public class DaoGradebookDefault implements com.github.hodcko.multy.dao.DaoGradebook {

    private static final Logger log = LoggerFactory.getLogger(DaoGradebookDefault.class);
    private final SessionFactory factory;

    public DaoGradebookDefault(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public int addGrade(int studentId, int cursId) {
        try {
            Session session =  factory.getCurrentSession();
            GradebookEntity gradebook = session.createQuery("select g from GradebookEntity g where g.studentId = :id and g.cursId = :cursId",
                    GradebookEntity.class).setParameter("id", studentId).setParameter("cursId", cursId).getSingleResult();
            gradebook.setGrade(gradebook.getGrade() + 1);
            log.info("increment grade by student with id {} and cursId {}", studentId, cursId);
            return studentId;
        }catch (HibernateError e){
            log.error("fail to increment grade by student with id {} and cursId {}", studentId, cursId, e);
        }
        return 0;
    }

    @Override

    public int addStudentToGradebook(int studentId, int cursId){
        try {
            Session session =  factory.getCurrentSession();
            GradebookEntity gradebook = new GradebookEntity(studentId, cursId, 0);
            session.save(gradebook);
            log.info("added to gradeBook student with id {} and cursId {}", studentId, cursId);
            return studentId;
        }catch (HibernateError e){
            log.error("fail to add to gradeBook student with id {} and cursId {}", studentId, cursId, e);
        }
        return 0;
    }

    @Override
    public int getGrade(int studentId, int cursId){
        try {
            Session session =  factory.getCurrentSession();
            int grade = (int) session.createQuery("select g.grade from GradebookEntity g where g.studentId = :id" +
                    " and g.cursId = :cursId")
                    .setParameter("id", studentId).setParameter("cursId", cursId).getSingleResult();
            log.info("student with id {} and cursId {} take grade {}", studentId, cursId, grade);
            return grade;
        }catch (HibernateError e){
            log.error("fail to get grade from student with id {} and cursId {}", studentId, cursId, e);
        }
        return 0;
    }

    @Override
    public boolean deleteStudentFromGradebook(int studentId){
        try {
            Session session =  factory.getCurrentSession();
            session.createQuery("delete from GradebookEntity g where g.studentId = :id")
                    .setParameter("id", studentId).executeUpdate();
            log.info("Student with id {}  deleted from GradeBook", studentId);
            return true;
        }catch (HibernateError e){
            log.error(" fail to delete Student with id {} from GradeBook", studentId, e);
        }
        return false;
    }

    @Override
    public boolean isExist(int studentId){
        try {
            Session session =  factory.getCurrentSession();
            List ids = session.createQuery("select g.studentId from GradebookEntity g where g.studentId = :id")
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
