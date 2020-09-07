package ru.makarovie.requestImpl;

import org.json.simple.JSONArray;
import ru.makarovie.db.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerByLastName extends CollectorCustomerToJson {
    private static final Connection CONNECTION = JdbcConnector.getConnection();

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
        PreparedStatement pstate =
                CONNECTION.prepareStatement("SELECT * FROM customer WHERE customer.second_name = ?");
        pstate.setObject(1, lastName);

        ResultSet resultSet = pstate.executeQuery();
        pstate.close();
        return CollectorCustomerToJson.getCustomersInJsonFormatFromResultSet(resultSet);
    }
}
