package ru.makarovie.requestImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.dbWorker.DistinctCustomerIdByDate;
import ru.makarovie.dbWorker.ProductsNameAndCostByDateAndCustomer;
import ru.makarovie.dbWorker.TotalAndAverageExpensesByPeriod;
import ru.makarovie.dbWorker.TotalCostumersExpensesByPeriod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public abstract class StatisticInPeriod {

    public static JSONArray getJsonArrayWithData(LocalDate startDate, LocalDate endDate)
            throws SQLException {
        JSONArray resultArray = new JSONArray();
        ResultSet idsOfCustomers;

        idsOfCustomers = DistinctCustomerIdByDate.getDatesFromDb(startDate, endDate);


        while (idsOfCustomers.next()) {
            int id = idsOfCustomers.getInt(1);
            resultArray.add(getDataAboutCostumersPurchasing(id, startDate, endDate));
        }

        resultArray.add(getTotalAndAvgExpenses(startDate, endDate));

        return resultArray;
    }

    private static JSONObject getDataAboutCostumersPurchasing
            (int id, LocalDate startDate, LocalDate endDate) throws SQLException {
        JSONObject productsOfOneCustomer = new JSONObject();
        ResultSet products = ProductsNameAndCostByDateAndCustomer
                .getDatesFromDb(id, startDate, endDate);
        JSONArray productsJson = CollectorProductsToJson
                .getProductsInJsonFormatFromResultSet(products);
        productsOfOneCustomer.put("name", id);
        productsOfOneCustomer.put("purchases", productsJson);
        productsOfOneCustomer.put("totalExpenses", getTotalExpensesForCostumer(id, startDate, endDate));
        return productsOfOneCustomer;
    }

    private static int getTotalExpensesForCostumer
            (int id, LocalDate startDate, LocalDate endDate) throws SQLException {
        int result = 0;

        ResultSet expenses = TotalCostumersExpensesByPeriod
                .getDatesFromDb(startDate, endDate, id);

        while (expenses.next()) {
            result = expenses.getInt("totalExpenses");
        }
        return result;
    }

    private static JSONObject getTotalAndAvgExpenses(LocalDate startDate, LocalDate endDate)
            throws SQLException {
        JSONObject totalAndAvgExpenses = new JSONObject();

        ResultSet expenses = TotalAndAverageExpensesByPeriod.getDatesFromDb(startDate, endDate);
        while (expenses.next()) {
            totalAndAvgExpenses.put("totalExpenses", expenses.getString("totalExpenses"));
            totalAndAvgExpenses.put("avgExpenses", expenses.getString("avgExpenses"));
        }
        return totalAndAvgExpenses;
    }
}
