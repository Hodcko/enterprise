package com.github.hodcko.multy.dao.impl;


import com.github.hodcko.multy.dao.utils.MysqlDataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoIsExistDefault implements com.github.hodcko.multy.dao.DaoIsExist {

    MysqlDataBase dataBase = new MysqlDataBase();
    private static volatile com.github.hodcko.multy.dao.DaoIsExist instance;
    private static final Logger log = LoggerFactory.getLogger(DaoIsExistDefault.class);



    public static com.github.hodcko.multy.dao.DaoIsExist getInstance(){
        com.github.hodcko.multy.dao.DaoIsExist localInstance = instance;
        if(localInstance == null){
            synchronized (com.github.hodcko.multy.dao.DaoIsExist.class){
                localInstance = instance;
                if(localInstance == null){
                    instance = localInstance = new DaoIsExistDefault();
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
