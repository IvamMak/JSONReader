package ru.makarovie.keyWorker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.errorChecker.*;
import ru.makarovie.requestImpl.StatisticInPeriod;

import java.sql.SQLException;
import java.time.LocalDate;

public class BetweenTwoDatesKeyWorker implements KeyWorker {

    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results = new JSONArray();
        BaseChecker checker = new AllKeysExistChecker("startDate", "endDate");
        checker.nextChecker(new AllValuesNotEmptyChecker("startDate", "endDate"))
        .nextChecker(new FirstDateLessThanSecond("startDate", "endDate"));

        if (checker.check(criteria, results)) {
            LocalDate startDate = LocalDate.parse((String) criteria.get("startDate"));
            LocalDate endDate = LocalDate.parse((String) criteria.get("endDate"));
            try {
                results = StatisticInPeriod.getJsonArrayWithData(startDate, endDate);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return results;
    }
}
