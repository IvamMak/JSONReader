package ru.makarovie.inputOutput;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class RequestParser {
    private JSONParser parser;
    private JSONObject request;
    private String fileForParsing;

    public RequestParser (String fileForParsing){
        this.fileForParsing = fileForParsing;
    }

    public JSONObject getJsonObjectWithRequest () {
        parser = new JSONParser();
        try (FileReader fileReader = new FileReader(fileForParsing)) {
            request = (JSONObject) parser.parse(fileReader);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return request;
    }
}
