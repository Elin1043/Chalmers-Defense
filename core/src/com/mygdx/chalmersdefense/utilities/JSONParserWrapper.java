package com.mygdx.chalmersdefense.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

public class JSONParserWrapper {
    private final JsonReader jsonParser = new JsonReader();     // Current Json parser
    private JsonValue mainObject;
    private JsonValue currentJsonValue;


    public JSONParserWrapper(String jsonPath) {
        mainObject = jsonParser.parse(Gdx.files.internal(jsonPath));
    }

    public JSONParserWrapper startParser() {
        currentJsonValue = mainObject;
        return this;
    }

    public JSONParserWrapper getJSONValue(String key) {
        currentJsonValue = currentJsonValue.get(key);
        return this;
    }

    public JSONParserWrapper getIndex(int index) {
        currentJsonValue = currentJsonValue.get(index);
        return this;
    }

    public String getString(String key) {
        return currentJsonValue.getString(key);
    }

    public Integer getInteger(String key) {
        return currentJsonValue.getInt(key);
    }

    public Float getFloat(String key) {
        return currentJsonValue.getFloat(key);
    }

    public HashMap<String, Double> getDoubleHashMap(String... keys) {
        HashMap<String, Double> hashMap = new HashMap<>();
        for (String key : keys) {
            hashMap.put(key, currentJsonValue.getDouble(key));
        }
        return new HashMap<>(hashMap);
    }
}
