package ru.makarovie.requestImpl;

import org.json.simple.JSONArray;
import ru.makarovie.db.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BadCustomerWithLimit {
    private static final Connection CONNECTION = JdbcConnector.getConnection();

    public static JSONArray getJsonArrayWithData(long limit){
        JSONArray jsonArray = null;
        try {
            jsonArray = getResultSetFromDb(limit);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return jsonArray;
    }

    private static JSONArray getResultSetFromDb(long limit) throws SQLException {
        PreparedStatement pstate =
                CONNECTION.prepareStatement("SELECT name, second_name FROM customer " +
                        "INNER JOIN purchase p on customer.id = p.customer_id " +
                        "GROUP BY customer.id ORDER BY COUNT(products) LIMIT ?");
        pstate.setObject(1, limit);

        ResultSet resultSet = pstate.executeQuery();
        pstate.close();
        return CollectorCustomerToJson.getCustomersInJsonFormatFromResultSet(resultSet);
    }
}
