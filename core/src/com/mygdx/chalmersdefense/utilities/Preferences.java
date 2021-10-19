package com.mygdx.chalmersdefense.utilities;

import java.util.HashMap;

final public class Preferences {
    private final HashMap<String, Boolean> booleanHashMap = new HashMap<>();
    private final HashMap<String, Float> floatHashMap = new HashMap<>();
    private final HashMap<String, Integer> integerHashMap = new HashMap<>();

    public Preferences() {
        putFloat("musicVolume", 0.2f);
        putFloat("soundEffectsVolume", 0);
        putBoolean("autoplay", true);
        putBoolean("muteMusic", true);
        putBoolean("muteSoundEffects", false);
        putInteger("refreshRate", 60);
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

    public void putInteger(String key, int value) {
        integerHashMap.put(key, value);
    }

    public float getInteger(String key) {
        return integerHashMap.get(key);
    }
}
