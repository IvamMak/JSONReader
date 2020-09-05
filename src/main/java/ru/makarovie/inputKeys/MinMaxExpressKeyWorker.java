package ru.makarovie.inputKeys;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.requestImpl.CustomerBetweenMinMaxExpress;

public class MinMaxExpressKeyWorker implements KeyWorker {
    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results;

        long minExpress = (Long) criteria.get("minExpress");
        long maxExpress = (Long) criteria.get("maxExpress");
        results = CustomerBetweenMinMaxExpress.getJsonArrayWithData(minExpress, maxExpress);
        return results;
    }
}
