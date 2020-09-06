package ru.makarovie;

import org.json.simple.JSONObject;
import ru.makarovie.inputOutput.RequestParser;
import ru.makarovie.inputOutput.ResultWriter;
import ru.makarovie.requestStrategy.RequestStrategy;
import ru.makarovie.requestStrategy.SearchRequestStrategy;
import ru.makarovie.requestStrategy.StatRequestStrategy;

import java.sql.SQLException;

public class Solution {
    public static void main(String[] args) throws SQLException {
        RequestStrategy requestStrategy;

        RequestParser requestParser =
                new RequestParser("src\\main\\resources\\files\\input.json");
        JSONObject jsonRequest = requestParser.getJsonObjectWithRequest();

        if (jsonRequest.containsKey("startDate") || jsonRequest.containsKey("endDate")) {
            requestStrategy = new StatRequestStrategy(jsonRequest);
        } else {
            requestStrategy = new SearchRequestStrategy(jsonRequest);
        }

        JSONObject jsonObject = requestStrategy.getJsonObjectWithDataFromDb();
        ResultWriter.writeResult(jsonObject);
    }
}
