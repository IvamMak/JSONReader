package ru.makarovie.keyWorker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface KeyWorker {
    JSONArray getResultsFromKey (JSONObject criteria);
}
