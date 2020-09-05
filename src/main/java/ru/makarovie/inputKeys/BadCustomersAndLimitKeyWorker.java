package ru.makarovie.inputKeys;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.requestImpl.BadCustomerWithLimit;

public class BadCustomersAndLimitKeyWorker implements KeyWorker{

    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results;
        long limit = (Long) criteria.get("badCustomers");
        results = BadCustomerWithLimit.getJsonArrayWithData(limit);
        return results;
    }
}
