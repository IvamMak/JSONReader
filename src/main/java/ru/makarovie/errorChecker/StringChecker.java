package ru.makarovie.errorChecker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StringChecker extends BaseChecker {
    private String[] keys;

    public StringChecker(String... keys) {
        this.keys = keys;
    }

    @Override
    public boolean check(JSONObject criteria, JSONArray results) {
        JSONObject error = new JSONObject();

        for (String key : keys) {
            String value = String.valueOf(criteria.get(key));
            if (!value.matches("[a-zA-Zа-яА-я]+")) {
                error.clear();
                error.put("type", "error");
                error.put("message", "поле " + key + " должно содержать только буквы");
                results.add(error);
            }
        }
        if (error.size() != 0) return false;
        return checkNext(criteria, results);
    }
}

