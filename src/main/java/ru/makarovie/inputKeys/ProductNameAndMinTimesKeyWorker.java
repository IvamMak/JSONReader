package ru.makarovie.inputKeys;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.requestImpl.CustomerByProductNameAndMinTimes;

public class ProductNameAndMinTimesKeyWorker implements KeyWorker{
    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results;
        String productName = (String) criteria.get("productName");
        long minTimes = (Long) criteria.get("minTimes");
        results = CustomerByProductNameAndMinTimes.getJsonArrayWithData(productName, minTimes);
        return results;
    }
}
