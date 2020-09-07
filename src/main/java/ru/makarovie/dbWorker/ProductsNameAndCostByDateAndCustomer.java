package ru.makarovie.dbWorker;

import ru.makarovie.db.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public abstract class ProductsNameAndCostByDateAndCustomer {
    private static final Connection CONNECTION = JdbcConnector.getConnection();

    public static ResultSet getDatesFromDb(int id, LocalDate startDate, LocalDate endDate)
            throws SQLException {
        PreparedStatement pstate =
                CONNECTION.prepareStatement("SELECT DISTINCT product_name, cost FROM product " +
                        "INNER JOIN purchase p on product.id = p.products " +
                        "WHERE purchase_date between ? AND ? AND customer_id = ?");
        pstate.setObject(1, startDate);
        pstate.setObject(2, endDate);
        pstate.setObject(3, id);

        ResultSet resultSet = pstate.executeQuery();
        pstate.close();
        return resultSet;
    }
}
