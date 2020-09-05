package ru.makarovie.requestStrategy;

import org.json.simple.JSONObject;

import java.sql.SQLException;

public interface RequestStrategy {
    JSONObject getJsonObjectWithDataFromDb() throws SQLException;
}
