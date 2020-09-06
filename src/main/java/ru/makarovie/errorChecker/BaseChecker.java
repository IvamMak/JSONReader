package ru.makarovie.errorChecker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class BaseChecker {
    private BaseChecker next;

    public BaseChecker nextChecker (BaseChecker next){
        this.next = next;
        return next;
    }

    public abstract boolean check (JSONObject criteria, JSONArray results);

    protected boolean checkNext(JSONObject criteria, JSONArray results) {
        if (next == null) return true;
        else return next.check(criteria, results);
    }
}
