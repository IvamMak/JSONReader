package ru.makarovie.keyWorker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.errorChecker.AllValuesNotEmptyChecker;
import ru.makarovie.errorChecker.BaseChecker;
import ru.makarovie.errorChecker.NegativeNumberChecker;
import ru.makarovie.errorChecker.NumberChecker;
import ru.makarovie.requestImpl.BadCustomerWithLimit;

public class BadCustomersAndLimitKeyWorker implements KeyWorker{

    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results = new JSONArray();

        BaseChecker checker = new AllValuesNotEmptyChecker("badCustomers");
        checker.nextChecker(new NumberChecker("badCustomers"))
                .nextChecker(new NegativeNumberChecker("badCustomers"));

        if (checker.check(criteria, results)) {
            long limit = (Long) criteria.get("badCustomers");
            results = BadCustomerWithLimit.getJsonArrayWithData(limit);
        }
        return results;
    }
}
