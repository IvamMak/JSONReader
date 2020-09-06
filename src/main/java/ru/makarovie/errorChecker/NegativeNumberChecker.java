package ru.makarovie.errorChecker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NegativeNumberChecker extends BaseChecker {
    private String[] keys;

    public NegativeNumberChecker(String... keys) {
        this.keys = keys;
    }

    @Override
    public boolean check(JSONObject criteria, JSONArray results) {
        JSONObject error = new JSONObject();
        for (String key : keys) {
            long value = (Long) criteria.get(key);
            if (value < 0) {
                error.put("type", "error");
                error.put("message", "поле " + key + " не может быть меньше нуля");
                results.add(error);
            }
        }
        if (error.size() != 0) return false;
        return checkNext(criteria, results);
    }
}
