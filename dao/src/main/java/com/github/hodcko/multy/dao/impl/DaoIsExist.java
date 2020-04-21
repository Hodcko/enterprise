package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.utils.MysqlDataBase;
import com.github.hodcko.multy.dao.IDaoIsExist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoIsExist implements IDaoIsExist {

    MysqlDataBase dataBase = new MysqlDataBase();
    private static volatile IDaoIsExist instance;
    private static final Logger log = LoggerFactory.getLogger(DaoIsExist.class);



    public static IDaoIsExist getInstance(){
        IDaoIsExist localInstance = instance;
        if(localInstance == null){
            synchronized (IDaoIsExist.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoIsExist();
                }
            }
        }
        return localInstance;
    }

    @Override
    public boolean isExist(String email, String userType){
        if (userType.equalsIgnoreCase("student")) {
            String mail = null;
            try (Connection connection = dataBase.connect();
                 PreparedStatement statement = connection.prepareStatement
                         ("select email from student where email = ?")) {
                statement.setString(1, email);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        mail = rs.getString("email");
                    }
                } catch (SQLException e) {
                    log.error("cant find student: {}", email, e);
                }
                if (email.equalsIgnoreCase(mail)) {
                    return true;
                }
            } catch (SQLException | ClassNotFoundException e) {
                log.error("cant find student: {}", email, e);

            }
        } else if (userType.equalsIgnoreCase("teacher")) {
            String mail = null;
            try (Connection connection = dataBase.connect();
                 PreparedStatement statement = connection.prepareStatement
                         ("select email from teacher where email = ?")) {
                statement.setString(1, email);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        mail = rs.getString("email");
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // logs
                }
                if (email.equalsIgnoreCase(mail)) {
                    return true;
                }
            } catch (SQLException | ClassNotFoundException e) {
                log.error("cant find teacher: {}", email, e);

            }
        }
        return false;
    }



}
