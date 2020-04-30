package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.utils.MysqlDataBase;
import com.github.hodcko.multy.model.DTOGroup;
import com.github.hodcko.multy.model.Student;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.ssl.HandshakeInStream;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DaoCursDefault implements DaoCurs {
    MysqlDataBase dataBase = new MysqlDataBase();
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
    public boolean deleteCurs(int cursId) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Curs curs = session.createQuery("select c from Curs c where id = :id", Curs.class)
                    .setParameter("id", cursId).getSingleResult();
            curs.setId(cursId);
            curs.setStart(null);
            curs.setEnd(null);
            session.getTransaction().commit();
            log.info("curs with id {} deleted", cursId);
            return true;
        }catch (HibernateError e){
            log.error("fail to delete curs with id {}", cursId);
        }
        return false;
    }

    @Override
    public List<DTOGroup> getMyStudents(int cursId, int numPage){
        int limit = (numPage - 1) * 1;
        try (Session session = SFUtil.getSession()) {
            session.getTransaction().begin();
            List<DTOGroup> groupDtos = session.createNativeQuery("select s.name, s.second_name as secondName , s.email, g.grade " +
                    "from Student s join Gradebook g on s.id = g.student_id  where s.curs_id = :id and g.grade > 0 limit :limit, 1")
                    .setParameter("id", cursId).setParameter("limit", limit)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("grade", StandardBasicTypes.INTEGER)
                    .setResultTransformer(Transformers.aliasToBean(DTOGroup.class))
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
            List<DTOGroup> groupDtos = session.createNativeQuery("select s.name, s.second_name as secondName , s.email, g.grade " +
                    "from Student s join Gradebook g on s.id = g.student_id  where s.curs_id = :id and g.grade > 0")
                    .setParameter("id", cursId)
                    .addScalar("secondName", StandardBasicTypes.STRING)
                    .addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("email", StandardBasicTypes.STRING)
                    .addScalar("grade", StandardBasicTypes.INTEGER)
                    .setResultTransformer(Transformers.aliasToBean(DTOGroup.class))
                    .list();
            session.getTransaction().commit();
            log.info("get count of students with curs id {}", cursId);
            return groupDtos.size();
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
            log.info("get classmates from curs with id {} {}",cursId, curs.getStudentList());
            return curs.getStudentList();
        }catch (HibernateException e){
            log.error("fail to get classmates from curs id {}", cursId, e);
        }return null;
    }

}
