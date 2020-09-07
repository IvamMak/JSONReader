package ru.makarovie.inputOutput;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public abstract class ResultWriter {

    public static void writeResult(JSONObject result) {
        try (FileWriter fileWriter =
                     new FileWriter("src\\main\\resources\\files\\output.json")) {
            result.writeJSONString(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
