package com.mygdx.chalmersdefense.utilities;

import java.util.HashMap;

/**
 * @author Daniel Persson
 * A class to hold prefrences for the game
 */
final public class Preferences {
    private final HashMap<String, Boolean> booleanHashMap = new HashMap<>();     //Hashmap of preferences using booleans
    private final HashMap<String, Float> floatHashMap = new HashMap<>();         //Hashmap of preferences using floats
    private final HashMap<String, Integer> integerHashMap = new HashMap<>();     //Hashmap of preferences using integers

    /**
     * Sets up default preferences
     */
    public Preferences() {
        putFloat("musicVolume", 0.2f);
        putFloat("soundEffectsVolume", 0);
        putBoolean("autoplay", false);
        putBoolean("muteMusic", true);
        putBoolean("muteSoundEffects", false);
        putInteger("refreshRate", 60);
    }

    /**
     * Put a new boolean value into hashmap
     * @param key preference
     * @param value value of preference
     */
    public void putBoolean(String key, boolean value) {
        booleanHashMap.put(key, value);
    }

    /**
     * Gets a boolean preference from hashmap
     * @param key preference
     * @return the preference
     */
    public boolean getBoolean(String key) {
        return booleanHashMap.get(key);
    }

    /**
     * Put a new float value into hashmap
     * @param key preference
     * @param value value of preference
     */
    public void putFloat(String key, float value) {
        floatHashMap.put(key, value);
    }

    /**
     * Gets a float preference from hashmap
     * @param key preference
     * @return the preference
     */
    public float getFloat(String key) {
        return floatHashMap.get(key);
    }

    /**
     * Put a new integer value into hashmap
     * @param key preference
     * @param value value of preference
     */
    public void putInteger(String key, int value) {
        integerHashMap.put(key, value);
    }

    /**
     * Gets a integer preference from hashmap
     * @param key preference
     * @return the preference
     */
    public float getInteger(String key) {
        return integerHashMap.get(key);
    }
}
