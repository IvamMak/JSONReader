package ru.makarovie.errorChecker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AllValuesNotEmptyChecker extends BaseChecker {
    private String[] keys;

    public AllValuesNotEmptyChecker(String... keys) {
        this.keys = keys;
    }

    @Override
    public boolean check(JSONObject criteria, JSONArray results) {
        JSONObject error = new JSONObject();

        for (String key : keys) {
            String value = String.valueOf(criteria.get(key));
            if (value.trim().length() == 0) {
                error.clear();
                error.put("type", "error");
                error.put("message", "поле " + key + " не может быть пустым");
                results.add(error);
            }
        }
        if (error.size() != 0) return false;
        return checkNext(criteria, results);
    }
}
