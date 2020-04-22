package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.utils.SFUtil;
import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.dao.DaoCurs;
import com.github.hodcko.multy.dao.utils.MysqlDataBase;
import com.github.hodcko.multy.model.DTOGroup;
import com.github.hodcko.multy.model.Student;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Curs getCurs(int curs_id) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Curs curs = session.createQuery("select c from Curs c where id = :id", Curs.class)
                    .setParameter("id", curs_id).getSingleResult();
            session.getTransaction().commit();
            log.info("curs get with id {}", curs_id);
            return curs;
        }catch (HibernateError e){
            log.error("fail to curs get with id {}", curs_id);
        }
        return null;
    }

    @Override
    public boolean deleteCurs(int curs_id) {
        try (Session session = SFUtil.getSession()) {
            session.beginTransaction();
            Curs curs = session.createQuery("select c from Curs c where id = :id", Curs.class)
                    .setParameter("id", curs_id).getSingleResult();
            curs.setStart(null);
            curs.setEnd(null);
            session.getTransaction().commit();
            log.info("curs with id {} deleted", curs_id);
            return true;
        }catch (HibernateError e){
            log.error("fail to delete curs with id {}", curs_id);
        }
        return false;
    }

    @Override
    public List<DTOGroup> getMyStudents(int curs_id){
//        try (Session session = SFUtil.getSession()) {
//            session.getTransaction();
//            List<DTOGroup> query = session.createNativeQuery("select name, second_name, email, grade" +
//                    "from student s join gradebook g on s.id = g.student_id where curs_id = :id and grade > 0"
//                    ,DTOGroup.class).setParameter("id", curs_id).getResultList();
//            session.getTransaction().commit();
//            log.info("get all students of curs get with id {}", curs_id);
//            return query;
//        }
        List<DTOGroup> list = new ArrayList<>();
        String name = null;
        String secondName = null;
        String email = null;
        int grade = 0;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select name, second_name, email, grade from student s\n" +
                             "join gradebook g on s.id = g.student_id\n" +
                             "where curs_id = ? and grade > 0\n")) {
            statement.setInt(1, curs_id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    name = rs.getString("name");
                    secondName = rs.getString("second_name");
                    email = rs.getString("email");
                    grade = rs.getInt("grade");
                    list.add(new DTOGroup(name, secondName, email, grade));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
