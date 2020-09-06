package ru.makarovie.inputKeys;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.errorChecker.AllValuesNotEmptyChecker;
import ru.makarovie.errorChecker.BaseChecker;
import ru.makarovie.errorChecker.StringChecker;
import ru.makarovie.requestImpl.CustomerByLastName;

public class LastNameKeyWorker implements KeyWorker {

    @Override
    public JSONArray getResultsFromKey(JSONObject criteria) {
        JSONArray results = new JSONArray();

        BaseChecker checker = new AllValuesNotEmptyChecker("lastName");
        checker.nextChecker(new StringChecker("lastName"));

        if (checker.check(criteria, results)) {
            String lastName = (String) criteria.get("lastName");
            results = CustomerByLastName.getJsonArrayWithData(lastName);
        }
        return results;
    }
}
