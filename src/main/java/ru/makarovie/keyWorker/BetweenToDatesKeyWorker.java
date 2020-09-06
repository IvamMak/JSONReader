package ru.makarovie.keyWorker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.errorChecker.AllKeysExistChecker;
import ru.makarovie.errorChecker.AllValuesNotEmptyChecker;
import ru.makarovie.errorChecker.BaseChecker;

import java.time.LocalDate;

public class BetweenToDatesKeyWorker implements KeyWorker {

    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results = new JSONArray();
        BaseChecker checker = new AllKeysExistChecker("startDate", "endDate");
        checker.nextChecker(new AllValuesNotEmptyChecker("startDate", "endDate"));

        if (checker.check(criteria, results)) {
            LocalDate startDate = LocalDate.parse((String) criteria.get("startDate"));
            LocalDate endDate = LocalDate.parse((String) criteria.get("endDate"));
        }
        return results;
    }
}
