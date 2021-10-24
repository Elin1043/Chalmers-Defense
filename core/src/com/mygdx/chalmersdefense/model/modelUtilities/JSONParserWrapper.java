package com.mygdx.chalmersdefense.model.modelUtilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

/**
 * @author Daniel Persson
 * A class for parsing JSON files and act as a JSON wrapper
 */
final public class JSONParserWrapper implements IParseJSON {
    private final JsonValue mainObject;  // Main object to parse
    private JsonValue currentJsonValue;  // Current (actively parsing) JSON object. JSONValue can either be an object, array, string, boolean, long, double or null.

    /**
     * A JSON parser for parsing JSON files
     * @param jsonPath path to JSON file
     */
    public JSONParserWrapper(String jsonPath) {
        JsonReader jsonParser = new JsonReader();
        mainObject = jsonParser.parse(Gdx.files.internal(jsonPath));
    }

    @Override
    public IParseJSON startParser() {
        currentJsonValue = mainObject;
        return this;
    }

    @Override
    public IParseJSON navThroughJSON(String key) throws NullPointerException {
        try {
            currentJsonValue = currentJsonValue.get(key);
        } catch (NullPointerException exception) {
            throw new NullPointerException(exception.getMessage());
        }
        return this;
    }

    @Override
    public IParseJSON getByIndex(int index) throws NullPointerException {
        try {
            currentJsonValue = currentJsonValue.get(index);
        } catch (NullPointerException exception) {
            throw new NullPointerException(exception.getMessage());
        }
        return this;
    }

    @Override
    public String getString(String key) throws IllegalArgumentException {
        String string;
        try {
            string = currentJsonValue.getString(key);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
        return string;
    }

    @Override
    public Integer getInteger(String key) throws IllegalArgumentException {
        int integer;
        try {
            integer = currentJsonValue.getInt(key);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
        return integer;
    }

    @Override
    public HashMap<String, Double> getDoubleHashMap(String... keys) throws IllegalArgumentException {
        HashMap<String, Double> hashMap = new HashMap<>();
        for (String key : keys) {
            try {
                hashMap.put(key, currentJsonValue.getDouble(key));
            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException(exception.getMessage());
            }
        }
        return new HashMap<>(hashMap);
    }
}
