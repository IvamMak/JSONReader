package ru.makarovie.requestStrategy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.keyWorker.BetweenTwoDatesKeyWorker;
import ru.makarovie.keyWorker.KeyWorker;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StatRequestStrategy implements RequestStrategy {
    private JSONObject criteria;
    private KeyWorker keyWorker;
    private JSONObject result = new JSONObject();

    @Override
    public JSONObject getJsonObjectWithDataFromDb() {
        JSONArray results = new JSONArray();

        result.put("type", "stat");
        result.put("totalDays", getTotalDays());

        if (criteria.containsKey("startDate") || criteria.containsKey("endDate")) {
            keyWorker = new BetweenTwoDatesKeyWorker();
            results = keyWorker.getResultsFromKey(criteria);
        }

        result.put("customers", results);

        return result;
    }

    public StatRequestStrategy(JSONObject request) {
        this.criteria = request;
    }

    private long getTotalDays() {
        LocalDate startDate = LocalDate.parse((String) criteria.get("startDate"));
        LocalDate endDate = LocalDate.parse((String) criteria.get("endDate"));
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
