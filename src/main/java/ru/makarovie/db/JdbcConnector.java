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
        Connection connection = JdbcConnector.getConnection();
        RequestParser requestParser = new RequestParser("src\\main\\resources\\files\\input.json");
        JSONObject jsonRequest = requestParser.getJsonObjectWithRequest();

        RequestStrategy requestStrategy = new SearchRequestStrategy(connection, jsonRequest);

        JSONObject jsonObject = requestStrategy.getJsonObjectWithDataFromDb();

/*        PreparedStatement pstate = connection.prepareStatement("SELECT * FROM customer WHERE customer.name = ?");

        JSONArray request = (JSONArray) jsonRequest.get("Customers");

        String reqName = null;

        for (int i = 0; i < request.size(); i++){
            JSONObject jsonObject = (JSONObject) request.get(i);
            System.out.println(jsonObject.toString());
            reqName = (String) jsonObject.get("name");
        }


        System.out.println(jsonRequest.toJSONString());
        System.out.println((String) jsonRequest.get("name"));

        pstate.setObject(1, reqName);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        ResultSet resultSet = pstate.executeQuery();

        while (resultSet.next()){
            JSONObject result = new JSONObject();
            result.put("name", resultSet.getString("name"));
            result.put("secondName", resultSet.getString("second_name"));
            Files.write(Paths.get("src\\main\\resources\\files\\output.json"), result.toJSONString().getBytes());
            jsonArray.add(result);
        }
        jsonObject.put("Customers", jsonArray);*/

        FileWriter fileWriter = new FileWriter("src\\main\\resources\\files\\output.json");
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.close();

        System.out.println(jsonObject);
    }
}
