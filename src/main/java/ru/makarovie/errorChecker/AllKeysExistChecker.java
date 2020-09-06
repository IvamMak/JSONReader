package ru.makarovie.errorChecker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AllKeysExistChecker extends BaseChecker {
    String[] keys;

    public AllKeysExistChecker(String... keys) {
        this.keys = keys;
    }

    @Override
    public boolean check(JSONObject criteria, JSONArray results) {
        JSONObject error = new JSONObject();

        for (String key : keys) {
            if (!criteria.containsKey(key)) {
                error.clear();
                error.put("type", "error");
                error.put("message", "не указан ключ " + key);
                results.add(error);
            }
        }
        if (error.size() != 0) return false;
        return checkNext(criteria, results);
    }
}
