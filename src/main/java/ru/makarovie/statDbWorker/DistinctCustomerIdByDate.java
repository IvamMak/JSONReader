package ru.makarovie.statDbWorker;

import ru.makarovie.db.JdbcConnector;

import java.sql.*;
import java.time.LocalDate;

public abstract class DistinctCustomerIdByDate {
    private static final Connection CONNECTION = JdbcConnector.getConnection();

    public static ResultSet getDatesFromDb(LocalDate startDate, LocalDate endDate)
            throws SQLException {
        PreparedStatement pstate =
                CONNECTION.prepareStatement("SELECT DISTINCT customer_id FROM purchase " +
                        "WHERE purchase_date between ? AND ?");
        pstate.setObject(1, startDate);
        pstate.setObject(2, endDate);

        ResultSet resultSet = pstate.executeQuery();
        pstate.close();
        return resultSet;
    }
}
