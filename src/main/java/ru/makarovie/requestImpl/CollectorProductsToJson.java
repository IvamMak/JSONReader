package ru.makarovie.requestImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CollectorProductsToJson {

    public static JSONArray getProductsInJsonFormatFromResultSet (ResultSet resultSet)
            throws SQLException {
        JSONArray resultArray = new JSONArray();

        while (resultSet.next()) {
            JSONObject products = new JSONObject();
            products.put("product_name", resultSet.getString("product_name"));
            products.put("cost", resultSet.getString("cost"));
            resultArray.add(products);
        }
        return resultArray;
    }
}
