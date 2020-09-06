package ru.makarovie.keyWorker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.errorChecker.*;
import ru.makarovie.requestImpl.CustomerByProductNameAndMinTimes;

public class ProductNameAndMinTimesKeyWorker implements KeyWorker{
    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results = new JSONArray();
        BaseChecker checker = new AllKeysExistChecker("productName", "minTimes");
        checker.nextChecker(new AllValuesNotEmptyChecker("productName", "minTimes"))
                .nextChecker(new NumberChecker("minTimes"))
                .nextChecker(new NegativeNumberChecker("minTimes"));

        if (checker.check(criteria, results)) {
            String productName = (String) criteria.get("productName");
            long minTimes = (Long) criteria.get("minTimes");
            results = CustomerByProductNameAndMinTimes.getJsonArrayWithData(productName, minTimes);
        }
        return results;
    }
}
