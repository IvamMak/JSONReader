package ru.makarovie.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/aikam_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    private static Connection connection = null;

    public static Connection getConnection (){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return connection;
    }

    private JdbcConnector (){
        throw new UnsupportedOperationException();
    }
}
