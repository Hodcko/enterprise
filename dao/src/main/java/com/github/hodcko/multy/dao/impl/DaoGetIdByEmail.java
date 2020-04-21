package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.IDaoGetIdByEmail;
import com.github.hodcko.multy.dao.utils.MysqlDataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class  DaoGetIdByEmail implements IDaoGetIdByEmail {

    MysqlDataBase dataBase = new MysqlDataBase();
    private static volatile IDaoGetIdByEmail instance;
    private static final Logger log = LoggerFactory.getLogger(DaoGetIdByEmail.class);



    public static IDaoGetIdByEmail getInstance(){
        IDaoGetIdByEmail localInstance = instance;
        if(localInstance == null){
            synchronized (IDaoGetIdByEmail.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoGetIdByEmail();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int getId(String email, String userType) {
        if (userType.equalsIgnoreCase("student")) {
            int id = 0;
            try (Connection connection = dataBase.connect();
                 PreparedStatement statement = connection.prepareStatement
                         ("select id from student where email = ?")) {
                statement.setString(1, email);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        id = rs.getInt("id");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return id;
            } catch (SQLException | ClassNotFoundException e) {
                log.error("cant get id:{}{}", email, userType, e);
            }
        } else if (userType.equalsIgnoreCase("teacher")) {
            int id = 0;
            try (Connection connection = dataBase.connect();
                 PreparedStatement statement = connection.prepareStatement
                         ("select id from teacher where email = ?")) {
                statement.setString(1, email);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        id = rs.getInt("id");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return id;
            } catch (SQLException | ClassNotFoundException e) {
                log.error("cant get id:{}{}", email, userType, e);
            }
        }
        return 0;
    }
}
