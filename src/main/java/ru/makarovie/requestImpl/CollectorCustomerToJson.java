package ru.makarovie.requestImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract class CollectorCustomerToJson {

    public static JSONArray getCustomersInJsonFormatFromResultSet (ResultSet resultSet) throws SQLException {
        JSONArray resultArray = new JSONArray();

        while (resultSet.next()) {
            JSONObject customers = new JSONObject();
            customers.put("name", resultSet.getString("name"));
            customers.put("lastName", resultSet.getString("second_name"));
            resultArray.add(customers);
        }
        return resultArray;
    }
}
