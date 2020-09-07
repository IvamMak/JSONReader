package ru.makarovie;

import org.json.simple.JSONObject;
import ru.makarovie.inputOutput.ConsoleHelper;
import ru.makarovie.inputOutput.RequestParser;
import ru.makarovie.inputOutput.ResultWriter;
import ru.makarovie.requestStrategy.RequestStrategy;
import ru.makarovie.requestStrategy.SearchRequestStrategy;
import ru.makarovie.requestStrategy.StatRequestStrategy;

import java.sql.SQLException;

public class Solution {
    public static void main(String[] args) throws SQLException {
        RequestStrategy requestStrategy = null;

        String typeOfOperation = ConsoleHelper.getTypeOfOperation();

        RequestParser requestParser =
                new RequestParser(ConsoleHelper.getInputFile());
        JSONObject jsonRequest = requestParser.getJsonObjectWithRequest();

        if (typeOfOperation.equals("stat")) {
            requestStrategy = new StatRequestStrategy(jsonRequest);
        } else if (typeOfOperation.equals("search")) {
            requestStrategy = new SearchRequestStrategy(jsonRequest);
        } else {
            System.out.println("Неверный тип операции (допустимые типы: stat, search)");
        }

        if (requestStrategy != null) {
            JSONObject jsonObject = requestStrategy.getJsonObjectWithDataFromDb();
            ResultWriter.writeResult(jsonObject);
        }
    }
}
