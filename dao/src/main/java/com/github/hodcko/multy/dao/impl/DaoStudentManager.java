package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.IDaoStudent;
import com.github.hodcko.multy.dao.MysqlDataBase;
import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class DaoStudentManager implements IDaoStudent {
    private static volatile IDaoStudent instance;
    private static final Logger log = LoggerFactory.getLogger(DaoStudentManager.class);
    MysqlDataBase dataBase = new MysqlDataBase();


    public static IDaoStudent getInstance(){
        IDaoStudent localInstance = instance;
        if(localInstance == null){
            synchronized (IDaoStudent.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoStudentManager();
                }
            }
        }
        return localInstance;
    }



    @Override
    public Student saveStudent(String name, String second_name, String email, int age, int curs_id){
        int id;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("insert into student(name, second_name, email, age, curs_id) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, second_name);
            statement.setString(3, email);
            statement.setInt(4, age);
            statement.setInt(5, curs_id);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            log.info("create student: {}{}{}", name, second_name, email);
            return new Student(id, name, second_name, email, age, curs_id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("fail to create student:{} {} {}", name, second_name, email, e);
        }
        return null;
    }

    @Override
    public Student getStudent(int id){
        String name = null;
        String secondName = null;
        String email = null;
        int age = 0;
        int cursId = 0;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select   name, second_name, email, age, curs_id from auth_user a\n" +
                             "join student s on  a.id = s.id\n" +
                             "where a.id = ?")) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    name = rs.getString("name");
                    secondName = rs.getString("second_name");
                    email = rs.getString("email");
                    age = rs.getInt("age");
                    cursId = rs.getInt("curs_id");
                }
                return new Student(id, name, secondName, email, age, cursId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("fail to get student:{}{}{}", name, secondName, email, e);
        }
        return null;
    }

    @Override
    public boolean deleteStudent(String email){
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from student where email = ? ")) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }




}
