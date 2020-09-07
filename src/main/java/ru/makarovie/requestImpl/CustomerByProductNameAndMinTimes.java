package ru.makarovie.requestImpl;

import org.json.simple.JSONArray;
import ru.makarovie.db.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerByProductNameAndMinTimes {
    private static final Connection CONNECTION = JdbcConnector.getConnection();

    public static JSONArray getJsonArrayWithData(String productName, long minTimes) {
        JSONArray jsonArray = null;
        try {
            jsonArray = getResultSetFromDb(productName, minTimes);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return jsonArray;
    }

    private static JSONArray getResultSetFromDb(String productName, long minTimes) throws SQLException {

        PreparedStatement pstate = CONNECTION.prepareStatement(
                "SELECT name, second_name FROM customer " +
                        "INNER JOIN purchase p ON customer.id = p.customer_id " +
                        "INNER JOIN product ON p.products = product.id " +
                        "WHERE product_name = ? " +
                        "GROUP BY customer.id HAVING count(*) >= ?");

        pstate.setObject(1, productName);
        pstate.setObject(2, minTimes);

        ResultSet resultSet = pstate.executeQuery();
        pstate.close();

        return CollectorCustomerToJson.getCustomersInJsonFormatFromResultSet(resultSet);
    }
}
