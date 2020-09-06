package ru.makarovie.errorChecker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NumberChecker extends BaseChecker {
    private String[] keys;

    public NumberChecker(String... keys){
        this.keys = keys;
    }

    @Override
    public boolean check(JSONObject criteria, JSONArray results) {
        JSONObject error = new JSONObject();

        for (String key: keys){
            try {
                long value = (Long) criteria.get(key);
            } catch (ClassCastException exception){
                error.clear();
                error.put("type", "error");
                error.put("message", "поле " + key + " может иметь только целочисленное значение, а также не должно содержать знака ковычек");
                results.add(error);
            }
        }
        if (error.size() != 0) return false;
        return checkNext(criteria, results);
    }
}
