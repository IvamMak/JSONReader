package ru.makarovie.requestStrategy;

import org.json.simple.JSONObject;
import ru.makarovie.keyWorker.BetweenToDatesKeyWorker;
import ru.makarovie.keyWorker.KeyWorker;

public class StatRequestStrategy implements RequestStrategy {
    private JSONObject request;
    private KeyWorker keyWorker;
    private JSONObject result = new JSONObject();

    @Override
    public JSONObject getJsonObjectWithDataFromDb() {
        if (request.containsKey("startDate") || request.containsKey("endDate")) {
            keyWorker = new BetweenToDatesKeyWorker();
            keyWorker.getResultsFromKey(request);
        }
        return new JSONObject();
    }

    public StatRequestStrategy(JSONObject request) {
        this.request = request;
    }
}
