package ru.makarovie.requestImpl;

import org.json.simple.JSONArray;
import ru.makarovie.db.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerBetweenMinMaxExpress {
    private static final Connection CONNECTION = JdbcConnector.getConnection();

    public static JSONArray getJsonArrayWithData(long minExpress, long maxExpress){
        JSONArray jsonArray = null;
        try {
            jsonArray = getResultSetFromDb(minExpress, maxExpress);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return jsonArray;
    }

    private static JSONArray getResultSetFromDb(long minExpress, long maxExpress) throws SQLException {
        PreparedStatement pstate =
                CONNECTION.prepareStatement("SELECT name, second_name FROM customer " +
                                "INNER JOIN purchase p on customer.id = p.customer_id " +
                                "INNER JOIN product p2 on p.products = p2.id " +
                                "GROUP BY customer.id HAVING sum(cost) BETWEEN ? AND ?");
        pstate.setObject(1, minExpress);
        pstate.setObject(2, maxExpress);

        ResultSet resultSet = pstate.executeQuery();
        pstate.close();
        return CollectorCustomerToJson.getCustomersInJsonFormatFromResultSet(resultSet);
    }
}
