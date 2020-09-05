package ru.makarovie.requestImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract class ResultSetTool {

    public static JSONArray getCustomersInJsonFormatFromResultSet (ResultSet resultSet) throws SQLException {
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
