package ru.makarovie.inputKeys;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.requestImpl.CustomerByLastName;

public class LastNameKeyWorker implements KeyWorker{

    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results;
        String lastName = (String) criteria.get("lastName");
        results = CustomerByLastName.getJsonArrayWithData(lastName);
        return results;
    }
}
