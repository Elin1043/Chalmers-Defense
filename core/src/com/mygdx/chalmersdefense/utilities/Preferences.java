package com.mygdx.chalmersdefense.utilities;

import java.util.HashMap;

public class Preferences {
    private HashMap<String, Boolean> booleanHashMap = new HashMap<>();
    private HashMap<String, Float> floatHashMap = new HashMap<>();

    public Preferences() {
        putFloat("musicVolume", 0.2f);
        putBoolean("autoplay", true);
    }

    public void putBoolean(String key, boolean value) {
        booleanHashMap.put(key, value);
    }

    public boolean getBoolean(String key) {
        return booleanHashMap.get(key);
    }

    public void putFloat(String key, float value) {
        floatHashMap.put(key, value);
    }

    public float getFloat(String key) {
        return floatHashMap.get(key);
    }
}
