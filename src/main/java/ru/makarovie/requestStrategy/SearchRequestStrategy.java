package ru.makarovie.requestStrategy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.makarovie.dataWorkers.LastNameDataWorker;

import java.sql.Connection;
import java.sql.SQLException;

public class SearchRequestStrategy implements RequestStrategy {
    private Connection connection;
    private JSONArray request;
    private JSONObject result = new JSONObject();

    @Override
    public JSONObject getJsonObjectWithDataFromDb() throws SQLException {
        JSONArray resultArray = new JSONArray();
        result.put("type", "search");

        for (int i = 0; i < request.size(); i++) {
            JSONObject jsonObject = (JSONObject) request.get(i);
            JSONObject tempObject = new JSONObject();

            if (jsonObject.get("lastName") != null) {
                String lastName = (String) jsonObject.get("lastName");
                tempObject.put("criteria", jsonObject);
                tempObject.put("results", LastNameDataWorker.getJsonArrayWithData(lastName));
                resultArray.add(tempObject);
            }
/*            productName.add((String) jsonObject.get("productName"));
            minMaxExpenses.add((Integer) jsonObject.get("minExpenses"));
            minMaxExpenses.add((Integer) jsonObject.get("maxExpenses"));
            badCustomers.add((Integer) jsonObject.get("badCustomers"));*/
        }
        result.put("results", resultArray);

        return result;
    }

/*    private void getDataFromDbWithLastNameCriteria(String lastName) throws SQLException {
        JSONArray resultArray = new JSONArray();

        PreparedStatement pstate =
                connection.prepareStatement("SELECT * FROM customer WHERE customer.second_name = ?");
        pstate.setObject(1, lastName);

        ResultSet resultSet = pstate.executeQuery();

        while (resultSet.next()) {
            JSONObject customersWithLastName = new JSONObject();
            customersWithLastName.put("name", resultSet.getString("name"));
            customersWithLastName.put("lastName", resultSet.getString("lastName"));
            resultArray.add(customersWithLastName);
        }
        result.put("results", resultArray);
        pstate.close();
    }*/

    public SearchRequestStrategy(Connection connection, JSONObject request) {
        this.connection = connection;
        this.request = (JSONArray) request.get("criterias");
    }
}
