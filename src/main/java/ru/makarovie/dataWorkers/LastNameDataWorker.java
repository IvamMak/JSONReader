package ru.makarovie.dataWorkers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.db.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LastNameDataWorker implements DataWorker{

    public static JSONArray getJsonArrayWithData(String lastName){
        JSONArray jsonArray = null;
        try {
            jsonArray = getResultSetFromDb(lastName);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return jsonArray;
    }

    private static JSONArray getResultSetFromDb(String lastName) throws SQLException {
        Connection connection = JdbcConnector.getConnection();

        PreparedStatement pstate =
                connection.prepareStatement("SELECT * FROM customer WHERE customer.second_name = ?");
        pstate.setObject(1, lastName);

        ResultSet resultSet = pstate.executeQuery();
        pstate.close();
        return collectJsonFromResultSet(resultSet);
    }

    private static JSONArray collectJsonFromResultSet(ResultSet resultSet) throws SQLException {
        JSONArray resultArray = new JSONArray();

        while (resultSet.next()) {
            JSONObject customersWithLastName = new JSONObject();
            customersWithLastName.put("name", resultSet.getString("name"));
            customersWithLastName.put("lastName", resultSet.getString("second_name"));
            resultArray.add(customersWithLastName);
        }
        return resultArray;
    }
}
