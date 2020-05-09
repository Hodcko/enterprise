package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.model.GroupDTO;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class DaoCursDefault implements DaoCurs {

    private static volatile DaoCurs instance;
    private static final Logger log = LoggerFactory.getLogger(DaoCursDefault.class);


    public static DaoCurs getInstance() {
        DaoCurs localInstance = instance;
        if (localInstance == null) {
            synchronized (DaoCurs.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoCursDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Curs createCurs(String names, LocalDate start, LocalDate end) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Curs curs = session.createQuery("select c from Curs c where name = :name", Curs.class)
                    .setParameter("name", names).getSingleResult();
            curs.setStart(start);
            curs.setEnd(end);
            session.getTransaction().commit();
            log.info("create curs {}, start {}, end {}", names, start, end);
            return curs;
        }catch (HibernateError e){
            log.error("fail to create curs {}, start {}, end {}", names, start, end, e);
        }
        return null;
    }

    @Override
    public boolean deleteCurs(int cursId) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            session.createQuery("update Curs c set c.start = null, c.end = null where c.id = :id")
                    .setParameter("id", cursId).executeUpdate();
            session.getTransaction().commit();
            log.info("curs with id {} deleted", cursId);
            return true;
        }catch (HibernateError e){
            log.error("fail to delete curs with id {}", cursId);
        }
        return false;
    }

    @Override
    public Curs getCurs(int cursId) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Curs curs = session.createQuery("select c from Curs c where id = :id", Curs.class)
                    .setParameter("id", cursId).getSingleResult();
            session.getTransaction().commit();
            log.info("curs get with id {}", cursId);
            return curs;
        }catch (HibernateError e){
            log.error("fail to curs get with id {}", cursId);
        }
        return null;
    }

    @Override
    public List<GroupDTO> getMyStudents(int cursId, int numPage){
        int offset = 3;
        int limit = (numPage - 1) * offset;
        try (Session session = SFUtil.getSession()) {
            session.getTransaction().begin();
            List<GroupDTO> groupDtos = session.createNativeQuery("select s.name, s.second_name as secondName , s.email, g.grade " +
                    "from Student s join Gradebook g on s.id = g.student_id  where g.curs_id = :id ")
                    .setParameter("id", cursId)//.setParameter("limit", limit)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("grade", StandardBasicTypes.INTEGER)
                    .setResultTransformer(Transformers.aliasToBean(GroupDTO.class))
                    .setFirstResult(limit)
                    .setMaxResults(offset)
                    .list();
            session.getTransaction().commit();
            log.info("get all students of curs with id {}", cursId);
            return groupDtos;
        }catch (HibernateException e){
            log.error("fail to get all students of curs with id {}", cursId);
        }
        return null;
    }



    @Override
    public int countOfStudents(int cursId){
        try (Session session = SFUtil.getSession()) {
            session.getTransaction().begin();
            Long count = (Long) session.createQuery("select count(s.name) " +
                    "from Student s join Gradebook g on s.id = g.studentId  where g.cursId = :id")
                    .setParameter("id", cursId).getSingleResult();
            session.getTransaction().commit();
            log.info("get count of students with curs id {}", cursId);
            return count.intValue();
        }catch (HibernateException e){
            log.error("fail to get count of students with curs id {}", cursId);
        }
        return 0;
    }


    @Override
    public List<Student> getClassmates(int cursId){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Curs curs = session.get(Curs.class, cursId);
            session.getTransaction().commit();
            log.info("get classmates from curs with id {} {}",cursId, curs.getStudents());
            return curs.getStudents();
        }catch (HibernateException e){
            log.error("fail to get classmates from curs id {}", cursId, e);
        }return null;
    }

    @Override
    public boolean inviteStudentOnCurs(int studentId, int cursId){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            student.getCurses().add(session.get(Curs.class, cursId));
            session.saveOrUpdate(student);
            session.getTransaction().commit();
            session.close();
            log.info("student with id {} invited on curs {}",studentId, cursId);
            return true;
        }catch (HibernateException e){
            log.error("fail to invite student with id {} on curs {}",studentId, cursId);
        }return false;
    }

    @Override
    public List<Teacher> getColleagues(int cursId){
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Curs curs = session.get(Curs.class, cursId);
            log.info("get colleagues from curs with id {} {}",cursId, curs.getTeachers());
            return curs.getTeachers();
        }catch (HibernateException e){
            log.error("fail to get colleagues from curs with id {} {}",cursId, e);
            return null;
        }
    }

}
