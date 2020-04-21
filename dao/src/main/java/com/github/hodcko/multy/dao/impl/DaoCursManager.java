package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.model.Curs;
import com.github.hodcko.multy.dao.IDaoCurs;
import com.github.hodcko.multy.dao.utils.MysqlDataBase;
import com.github.hodcko.multy.model.DTOGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCursManager implements IDaoCurs {
    MysqlDataBase dataBase = new MysqlDataBase();
    private static volatile IDaoCurs instance;
    private static final Logger log = LoggerFactory.getLogger(DaoCursManager.class);


    public static IDaoCurs getInstance() {
        IDaoCurs localInstance = instance;
        if (localInstance == null) {
            synchronized (IDaoCurs.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoCursManager();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Curs createCurs(String names, Date start, Date end) {
        int id = 0;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("update curs set start_date = ?, end_date = ?  where name = ?", Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, start);
            statement.setDate(2, end);
            statement.setString(3, names);

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            log.info("create curs: {}", names);
        } catch (SQLException | ClassNotFoundException e) {
            log.error("fail to create curs: {}", names, e);
        }
        return new Curs(id, names, start, end);
    }

    @Override
    public Curs getCurs(int curs_id) {
        String name = null;
        Date start = null;
        Date end = null;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select * from curs where id = ?")) {
            statement.setInt(1, curs_id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    name = rs.getString("name");
                    start = rs.getDate("start_date");
                    end = rs.getDate("end_date");
                }
                log.info("curs get: {}", name);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Curs(curs_id, name, start, end);
    }

    @Override
    public List<DTOGroup> getMyStudents(int curs_id){
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
