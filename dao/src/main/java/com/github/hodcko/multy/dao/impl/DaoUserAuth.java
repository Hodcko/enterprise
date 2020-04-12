package com.github.hodcko.multy.dao.impl;

import com.github.hodcko.multy.model.AuthUser;
import com.github.hodcko.multy.dao.IDaoAuth;
import com.github.hodcko.multy.dao.MysqlDataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;


public class DaoUserAuth implements IDaoAuth {
    private static final Logger log = LoggerFactory.getLogger(DaoUserAuth.class);
    MysqlDataBase dataBase = new MysqlDataBase();
    private static volatile IDaoAuth instance;

    public static IDaoAuth getInstance() {
        IDaoAuth localInstance = instance;
        if (localInstance == null) {
            synchronized (IDaoAuth.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaoUserAuth();
                }
            }
        }
        return localInstance;
    }



    @Override
    public String getByLogin(String password){
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select login from auth_user where password = ?")) {
            statement.setString(1, password);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return rs.getString("login");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
        public AuthUser saveAuthUser (int user_id, String login, String password, String role) {
            try (Connection connection = dataBase.connect();
                 PreparedStatement statement = connection.prepareStatement
                         ("insert into auth_user(id, login, password, Role) values(?,?,?,?)")) {
                statement.setInt(1, user_id);
                statement.setString(2, login);
                statement.setString(3, password);
                statement.setString(4, role);
                statement.executeUpdate();
                log.info("create authUser: {}{}{}{}", login, password, role, user_id);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                log.error("fail to create authUser: {}{}{}{}", login, password, role, user_id, e);

            }
            return new AuthUser(login, password, role, user_id);
        }

        @Override
        public String passwordGenerate(String email, String userType){
            String password = null;
            if(userType.equalsIgnoreCase("student")) {
                try (Connection connection = dataBase.connect();
                     PreparedStatement statement = connection.prepareStatement
                             ("select second_name, id from student where email = ?")) {
                    statement.setString(1, email);
                    try (ResultSet rs = statement.executeQuery()) {
                        while (rs.next()) {
                            String secondName = rs.getString("second_name");
                            int id = rs.getInt("id");
                            password = (secondName + id);
                            log.info("student password generated: {}{}{}", email, password, userType);
                        }
                    }
                    return password;
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try (Connection connection = dataBase.connect();
                     PreparedStatement statement = connection.prepareStatement
                             ("select second_name, id from teacher where email = ?")) {
                    statement.setString(1, email);
                    try (ResultSet rs = statement.executeQuery()) {
                        while (rs.next()) {
                            String secondName = rs.getString("second_name");
                            int id = rs.getInt("id");
                            password = (secondName + id);
                        }
                    }
                    log.info("teacher password generated: {}{}{}", email, password, userType);
                    return password;
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    @Override
    public boolean deleteAuthUser (int id, String role) {
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from auth_user where id = ? and Role = ?")) {
            statement.setInt(1, id);
            statement.setString(2, role);
            statement.executeUpdate();
            log.info("deleted authUser: {}{}", id, role);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("cant delete authUser: {}{}", id, role,e );

        }
        return true;
    }

    @Override
    public String getRole(String login, String password){
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select Role from auth_user where password = ? and login = ?")) {
            statement.setString(1, password);
            statement.setString(2, login);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return rs.getString("Role");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AuthUser getAuthUser(String login, String password){
        int id = 0;
        String role = null;
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("select id, Role from auth_user where password = ? and login = ?")) {
            statement.setString(1, password);
            statement.setString(2, login);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                   role = rs.getString("Role");
                   id = rs.getInt("id");
                }
                return new AuthUser(login, password, role , id);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("fail to get authUser: {}{}", login, password, e);
        }
        return null;
    }

    @Override
    public boolean changePassword(String login, String password, String newPassword){
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement
                     ("update auth_user set password = ? where login = ? and password = ?")) {
            statement.setString(1, newPassword);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.executeUpdate();
            log.info("password changed: {}{}{}", login, password, newPassword);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("fail to change password: {}{}{}", login, password, newPassword, e);

        }
        return false;
    }

}
