package ru.makarovie.errorChecker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;

public class FirstDateLessThanSecond extends BaseChecker{
    private String startDate;
    private String endDate;

    public FirstDateLessThanSecond(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean check(JSONObject criteria, JSONArray results) {
        JSONObject error = new JSONObject();
        LocalDate startDate = LocalDate.parse((String) criteria.get("startDate"));
        LocalDate endDate = LocalDate.parse((String) criteria.get("endDate"));

        if (startDate.isAfter(endDate)) {
            error.put("type", "error");
            error.put("message", "Первая дата не может быть больше второй");
            results.add(error);
        }
        if (error.size() != 0) return false;
        return checkNext(criteria, results);
    }
}
