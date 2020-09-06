package ru.makarovie.errorChecker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FirstNumberLessThanSecondChecker extends BaseChecker {
    private String firstKey;
    private String secondKey;

    public FirstNumberLessThanSecondChecker(String firstKey, String secondKey) {
        this.firstKey = firstKey;
        this.secondKey = secondKey;
    }

    @Override
    public boolean check(JSONObject criteria, JSONArray results) {
        JSONObject error = new JSONObject();
        long firstValue = (Long) criteria.get(firstKey);
        long secondValue = (Long) criteria.get(secondKey);

        if (firstValue > secondValue) {
            error.put("type", "error");
            error.put("message", "поле " + firstKey + " не может быть больше " + secondKey);
            results.add(error);
        }
        if (error.size() != 0) return false;
        return checkNext(criteria, results);
    }
}
