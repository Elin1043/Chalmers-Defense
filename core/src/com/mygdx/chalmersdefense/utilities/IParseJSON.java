package com.mygdx.chalmersdefense.utilities;

import java.util.HashMap;

/**
 * @author Daniel Persson
 * A interface for JSON parsers
 */
public interface IParseJSON {
    /**
     * Starts the parser with the main object
     * @return this object to enable method chaining
     */
    IParseJSON startParser();

    /**
     * Navigates trough JSON and sets currentJsonValue to a new JSON value depending on inputted key
     * @param key to JSON value
     * @return this object to enable method chaining
     * @throws NullPointerException if inputted key leads to a null object
     */
    IParseJSON navThroughJSON(String key) throws NullPointerException;

    /**
     * Sets currently JSON value to a new JSON value depending on inputted index
     * @param index to get JSON value from
     * @return this object to enable method chaining
     * @throws NullPointerException if inputted index leads to a null object
     */
    IParseJSON getByIndex(int index) throws NullPointerException;

    /**
     * Gets String value of current JSON value based on inputted key
     * @param key to JSON value
     * @return a String of a JSON value
     * @throws IllegalArgumentException if inputted key leads to a null object
     */
    String getString(String key) throws IllegalArgumentException;

    /**
     * Gets Integer value of current JSON value based on inputted key
     * @param key to JSON value
     * @return a Integer of a JSON value
     * @throws IllegalArgumentException if inputted key leads to a null object
     */
    Integer getInteger(String key) throws IllegalArgumentException;

    /**
     * Gets multiple Double values based on multiple inputted keys
     * @param keys an infinite amount of keys
     * @return a HashMap with Double values of current JSON value
     * @throws IllegalArgumentException if inputted keys leads to a null object
     */
    HashMap<String, Double> getDoubleHashMap(String... keys) throws IllegalArgumentException;
}
