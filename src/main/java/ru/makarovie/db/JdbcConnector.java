package ru.makarovie.db;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import ru.makarovie.RequestParser;
import ru.makarovie.requestStrategy.RequestStrategy;
import ru.makarovie.requestStrategy.SearchRequestStrategy;

import java.io.FileWriter;
import java.io.IOException;
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

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        RequestParser requestParser = new RequestParser("src\\main\\resources\\files\\input.json");
        JSONObject jsonRequest = requestParser.getJsonObjectWithRequest();

        RequestStrategy requestStrategy = new SearchRequestStrategy(jsonRequest);

        JSONObject jsonObject = requestStrategy.getJsonObjectWithDataFromDb();

        FileWriter fileWriter = new FileWriter("src\\main\\resources\\files\\output.json");
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.close();

        System.out.println(jsonObject);
    }
}
