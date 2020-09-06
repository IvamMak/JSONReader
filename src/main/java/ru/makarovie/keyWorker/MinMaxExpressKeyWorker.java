package ru.makarovie.keyWorker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.errorChecker.*;
import ru.makarovie.requestImpl.CustomerBetweenMinMaxExpress;

public class MinMaxExpressKeyWorker implements KeyWorker {
    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results = new JSONArray();

        BaseChecker checker = new AllKeysExistChecker("minExpress", "maxExpress");
        checker.nextChecker(new AllValuesNotEmptyChecker("minExpress", "maxExpress"))
                .nextChecker(new NumberChecker("minExpress", "maxExpress"))
                .nextChecker(new NegativeNumberChecker("minExpress", "maxExpress"))
                .nextChecker(new FirstNumberLessThanSecondChecker("minExpress", "maxExpress"));

        if (checker.check(criteria, results)) {
            long minExpress = (Long) criteria.get("minExpress");
            long maxExpress = (Long) criteria.get("maxExpress");
            results = CustomerBetweenMinMaxExpress.getJsonArrayWithData(minExpress, maxExpress);
        }
        return results;
    }
}
