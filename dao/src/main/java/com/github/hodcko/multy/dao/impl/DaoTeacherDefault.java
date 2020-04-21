package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.DaoTeacher;
import com.github.hodcko.multy.dao.utils.MysqlDataBase;
import com.github.hodcko.multy.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class DaoTeacherDefault implements DaoTeacher {
    private static volatile DaoTeacher instance;
    MysqlDataBase dataBase = new MysqlDataBase();
    private static final Logger log = LoggerFactory.getLogger(DaoTeacherDefault.class);


    public static DaoTeacher getInstance(){
        DaoTeacher localInstance = instance;
        if(localInstance == null){
            synchronized (DaoTeacher.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoTeacherDefault();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Teacher saveTeacher(String name, String second_name, String email, int curs_id){
        int id = 0;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("insert into teacher(name, second_name, email, curs_id) values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, second_name);
            statement.setString(3, email);
            statement.setInt(4, curs_id);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            log.info("create teacher with name {} second name{} email {}", name, second_name, email);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("fail to create teacher with name {} second name {}  email {}", name, second_name, email, e);
        }
        return new Teacher(id, name, second_name, email, curs_id);
    }

    @Override
    public Teacher getTeacher(int id){
        String name = null;
        String secondName = null;
        String email = null;
        int cursId = 0;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select   name, second_name, email, curs_id from auth_user a\n" +
                             "join teacher s on  a.id = s.id\n" +
                             "where a.id = ?")) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    name = rs.getString("name");
                    secondName = rs.getString("second_name");
                    email = rs.getString("email");
                    cursId = rs.getInt("curs_id");
                }
                return new Teacher(id, name, secondName, email, cursId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("fail to get teacher with name {} second name {} email {}", name, secondName, email, e);
        }
        return null;
    }

    @Override
    public boolean deleteTeacher(String email){
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from teacher where email = ? ")) {
            statement.setString(1, email);
            statement.executeUpdate();
            log.info("teacher with email {} deleted ", email);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }
}
