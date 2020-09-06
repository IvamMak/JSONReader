package ru.makarovie.requestStrategy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.inputKeys.*;

import java.sql.Connection;

public class SearchRequestStrategy implements RequestStrategy {
    private JSONArray request;
    private JSONObject result = new JSONObject();
    private KeyWorker keyWorker;

    @Override
    public JSONObject getJsonObjectWithDataFromDb() {
        JSONArray resultsArray = new JSONArray();
        result.put("type", "search");

        for (int i = 0; i < request.size(); i++) {
            JSONArray results = null;
            JSONObject criteria = (JSONObject) request.get(i);

            if (criteria.containsKey("lastName")) {
                keyWorker = new LastNameKeyWorker();
                results = keyWorker.getResultsFromKey(criteria);
            } else if (criteria.containsKey("productName") || criteria.containsKey("minTimes")) {
                keyWorker = new ProductNameAndMinTimesKeyWorker();
                results = keyWorker.getResultsFromKey(criteria);
            } else if (criteria.containsKey("minExpenses") || criteria.containsKey("maxExpress")){
                keyWorker = new MinMaxExpressKeyWorker();
                results = keyWorker.getResultsFromKey(criteria);
            } else if (criteria.containsKey("badCustomers")){
                keyWorker = new BadCustomersAndLimitKeyWorker();
                results = keyWorker.getResultsFromKey(criteria);
            }

            resultsArray.add(getJsonObjectWithCriteriaResults(criteria, results));

        }
        result.put("results", resultsArray);

        return result;
    }

    private JSONObject getJsonObjectWithCriteriaResults(JSONObject criteria, JSONArray results) {
        JSONObject tempObject = new JSONObject();
        tempObject.put("criteria", criteria);
        tempObject.put("results", results);
        return tempObject;
    }

    public SearchRequestStrategy(JSONObject request) {
        this.request = (JSONArray) request.get("criterias");
    }
}
