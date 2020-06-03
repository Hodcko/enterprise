package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.model.GroupDTO;
import com.github.hodcko.multy.model.Student;
import com.github.hodcko.multy.model.Teacher;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDate;
import java.util.List;

public class DaoCursDefault implements DaoCurs {


    private static final Logger log = LoggerFactory.getLogger(DaoCursDefault.class);
    private final SessionFactory factory;

    public DaoCursDefault(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public Curs createCurs(String names, LocalDate start, LocalDate end) {
        try {
            Session session =  factory.getCurrentSession();
            Curs curs = session.createQuery("select c from Curs c where name = :name", Curs.class)
                    .setParameter("name", names).getSingleResult();
            curs.setStart(start);
            curs.setEnd(end);
            log.info("create curs {}, start {}, end {}", names, start, end);
            return curs;
        }catch (HibernateError e){
            log.error("fail to create curs {}, start {}, end {}", names, start, end, e);
        }
        return null;
    }

    @Override
    public boolean deleteCurs(int cursId) {
        try {
            Session session =  factory.getCurrentSession();
            session.createQuery("update Curs c set c.start = null, c.end = null where c.id = :id")
                    .setParameter("id", cursId).executeUpdate();
            log.info("curs with id {} deleted", cursId);
            return true;
        }catch (HibernateError e){
            log.error("fail to delete curs with id {}", cursId, e);
        }
        return false;
    }

    @Override
    public Curs getCurs(int cursId) {
        try {
            Session session =  factory.getCurrentSession();
            Curs curs = session.get(Curs.class, cursId);
            log.info("curs get with id {}", cursId);
            return curs;
        }catch (HibernateError e){
            log.error("fail to curs get with id {}", cursId, e);
        }
        return null;
    }

    @Override
    public List<GroupDTO> getMyStudents(int cursId, int numPage){
        int offset = 3;
        int limit = (numPage - 1) * offset;
        try {
            Session session =  factory.getCurrentSession();
            List<GroupDTO> groupDtos = session.createNativeQuery("select s.name, s.second_name as secondName , s.email, g.grade " +
                    "from Student s join Gradebook g on s.id = g.student_id  where g.curs_id = :id ")
                    .setParameter("id", cursId)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("grade", StandardBasicTypes.INTEGER)
                    .setResultTransformer(Transformers.aliasToBean(GroupDTO.class))
                    .setFirstResult(limit)
                    .setMaxResults(offset)
                    .list();
            log.info("get all students of curs with id {}", cursId);
            return groupDtos;
        }catch (HibernateException e){
            log.error("fail to get all students of curs with id {}", cursId, e);
        }
        return null;
    }



    @Override
    public int countOfStudents(int cursId){
        try {
            Session session =  factory.getCurrentSession();
            Long count = (Long) session.createQuery("select count(s.name) " +
                    "from Student s join Gradebook g on s.id = g.studentId  where g.cursId = :id")
                    .setParameter("id", cursId).getSingleResult();
            log.info("get count of students with curs id {}", cursId);
            return count.intValue();
        }catch (HibernateException e){
            log.error("fail to get count of students with curs id {}", cursId, e);
        }
        return 0;
    }

    @Override

    public boolean inviteStudentOnCurs(int studentId, int cursId){
        try {
            Session session =  factory.getCurrentSession();
            Student student = session.get(Student.class, studentId);
            student.getCurses().add(session.get(Curs.class, cursId));
            session.saveOrUpdate(student);
            log.info("student with id {} invited on curs {}",studentId, cursId);
            return true;
        }catch (HibernateException e){
            log.error("fail to invite student with id {} on curs {}",studentId, cursId, e);
        }return false;
    }

    @Override
    public List<Teacher> getColleagues(int cursId){
        try {
            Session session = factory.getCurrentSession();;
            Curs curs = session.get(Curs.class, cursId);
            log.info("get colleagues from curs with id {} {}",cursId, curs.getTeachers());
            return curs.getTeachers();
        }catch (HibernateException e){
            log.error("fail to get colleagues from curs with id {} {}", cursId, e);
            return null;
        }
    }

    @Override

    public List<Student> getClassmates(int cursId){
        try {
            Session session = factory.getCurrentSession();
            Curs curs = session.get(Curs.class, cursId);
            log.info("get classmates from curs with id {} {}",cursId, curs.getStudents());
            return curs.getStudents();
        }catch (HibernateException e) {
            log.error("fail to get classmates from curs id {}", cursId, e);
            return null;
        }
    }

}
