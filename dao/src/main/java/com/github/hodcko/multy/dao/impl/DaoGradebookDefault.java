package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Gradebook;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
    public int addGrade(int studetn_id) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Gradebook gradebook = session.createQuery("select g from Gradebook g where studentId = :id",
                    Gradebook.class).setParameter("id", studetn_id).getSingleResult();
            gradebook.setGrade(gradebook.getGrade() + 1);
            session.getTransaction().commit();
            log.info("increment grade by student with id {}", studetn_id);
            return studetn_id;
        }catch (HibernateError e){
            log.error("fail to increment grade by student with id {}", studetn_id, e);
        }
        return 0;
    }

    @Override
    public int addStudentToGradebook(int studetn_id){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Gradebook gradebook = new Gradebook(studetn_id, 0);
            session.saveOrUpdate(gradebook);
            session.getTransaction().commit();
            log.info("added to gradeBook student with id {}", studetn_id);
            return studetn_id;
        }catch (HibernateError e){
            log.error("fail to add to gradeBook student with id {}", studetn_id, e);
        }
        return 0;
    }

    @Override
    public int getGrade(int studetn_id){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            int grade = (int) session.createNativeQuery("select grade from gradebook where student_id = :id")
                    .setParameter("id", studetn_id).getSingleResult();
            session.getTransaction().commit();
            log.info("student with id {} take grade {}", studetn_id, grade);
            return grade;
        }catch (HibernateError e){
            log.error("fail to get grade from student with id {}", studetn_id, e);
        }
        return 0;
    }

    @Override
    public boolean deleteStudentFromGradebook(int student_id){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Gradebook gradebook = session.createQuery("select g from Gradebook g where studentId = :id",
                    Gradebook.class).setParameter("id", student_id).getSingleResult();
            session.delete(gradebook);
            session.getTransaction().commit();
            log.info("Student with id {} deleted from GradeBook", student_id);
            return true;
        }catch (HibernateError e){
            log.error(" fail to delete Student with id {} from GradeBook", student_id);
        }
        return false;
    }
}
