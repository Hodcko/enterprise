package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.dao.IDaoGradebook;
import com.github.hodcko.multy.dao.MysqlDataBase;
import com.github.hodcko.multy.model.DTOGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoGradebook implements IDaoGradebook {
    private static volatile IDaoGradebook instance;
    MysqlDataBase dataBase = new MysqlDataBase();
    private static final Logger log = LoggerFactory.getLogger(DaoGradebook.class);



    public static IDaoGradebook getInstance(){
        IDaoGradebook localInstance = instance;
        if(localInstance == null){
            synchronized (IDaoGradebook.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoGradebook();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int addGrade(int studetn_id) {
        int id = 0;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("UPDATE gradebook SET grade = grade + 1 where student_id = ?;")) {
            statement.setInt(1, studetn_id);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studetn_id;
    }

    @Override
    public int addStudentToGradebook(int studetn_id){
        int id = 0;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("insert into gradebook(student_id) values(?)")) {
            statement.setInt(1, studetn_id);
            statement.executeUpdate();
            log.info("added to gradeBook: {}", studetn_id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("cant add to gradeBook: {}", studetn_id, e);

        }
        return studetn_id;
    }

    @Override
    public int getGrade(int studetn_id){
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select grade from gradebook where student_id = ?")) {
            statement.setInt(1, studetn_id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("grade");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("cant get grade: {}", studetn_id, e);
        }
        return 0;
    }

    @Override
    public boolean deleteStudentFromGradebook(int student_id){
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from gradebook where student_id = ? ")) {
            statement.setInt(1, student_id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }



}
