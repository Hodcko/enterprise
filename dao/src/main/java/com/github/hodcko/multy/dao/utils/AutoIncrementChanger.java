package com.github.hodcko.multy.dao.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AutoIncrementChanger {
    public static MysqlDataBase dataBase = new MysqlDataBase();

    public static void changeAutoIncrement(String tableName){
        String changer = "ALTER TABLE "+ tableName + " auto_increment = 0";
        try {
            Connection connection = dataBase.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(changer);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
