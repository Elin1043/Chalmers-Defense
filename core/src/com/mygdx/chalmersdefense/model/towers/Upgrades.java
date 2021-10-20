package com.mygdx.chalmersdefense.model.towers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Daniel Persson
 * A class for storing and applying upgrades to towers.
 */
public abstract class Upgrades {
    private static final JSONParser parser = new JSONParser();     // Current Json parser
    private static final int MAX_UPGRADES = 3;                     // Current max upgrade level
    private static final JSONObject mainObject = getMainJSONObject();                          // The parsed json object

    private static JSONObject getMainJSONObject() {
        try {
            return (JSONObject) parser.parse(new InputStreamReader(Objects.requireNonNull(Upgrades.class.getClassLoader().getResourceAsStream("UpgradeData.json"))));
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Main method for upgrading a tower.
     *
     * @param tower tower to be upgraded
     * @return if upgrade is successful
     */
    public static boolean upgradeTower(ITower tower) {
        // If tower is max upgraded don't upgrade
        if (tower.getUpgradeLevel() >= MAX_UPGRADES) return false;

        HashMap<String, Double> upgrades = getTowerUpgradeData(tower.getName(), tower.getUpgradeLevel());

        tower.upgradeTower(upgrades);
        return true;
    }

    /**
     * Gets title of tower upgrade from database in regard to upgrade level.
     *
     * @param towerName    The towers name
     * @param upgradeLevel level of upgrade to get title from
     * @return a String with towers upgrade title depending on upgrade level.
     */
    public static String getTowerUpgradeTitle(String towerName, int upgradeLevel) {
        String title = "";
        try {
            JSONObject upgradeObject = getUpgradeObject(towerName, upgradeLevel);
            title = upgradeObject.get("title").toString();
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        return title;
    }

    /**
     * Gets description of tower upgrade from database in regard to upgrade level.
     *
     * @param towerName    used to get towers description
     * @param upgradeLevel level up upgrade to get description of
     * @return a String with towers upgrade description depending on upgrade level.
     */
    public static String getTowerUpgradeDesc(String towerName, int upgradeLevel) {
        String desc = "";
        try {
            JSONObject upgradeObject = getUpgradeObject(towerName, upgradeLevel);
            desc = upgradeObject.get("desc").toString();
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        return desc;
    }

    /**
     * Gets price of tower upgrade from database in regard to upgrade level.
     *
     * @param towerName    used to get towers price
     * @param upgradeLevel level of upgrade to get price from
     * @return a Long with towers upgrade price depending on upgrade level.
     */
    public static Long getTowerUpgradePrice(String towerName, int upgradeLevel) {
        Long price = 0L;
        try {
            JSONObject upgradeObject = getUpgradeObject(towerName, upgradeLevel);
            price = (Long) upgradeObject.get("price");
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        return price;
    }

    private static JSONObject getUpgradeObject(String towerName, int upgradeLevel) throws NullPointerException {
        JSONObject upgradeObject;
        try {
            JSONArray towerUpgradeArray = (JSONArray) mainObject.get(towerName);
            upgradeObject = (JSONObject) towerUpgradeArray.get(upgradeLevel - 1);
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException();
        }

        return upgradeObject;
    }

    /**
     * Generates a HashMap with appropriate upgrade data from a JSON object and returns a copy of the HashMap.
     *
     * @param towerName    to get upgrades from
     * @param upgradeLevel upgrade level to get upgrades from
     * @return a HashMap with upgrade data.
     */
    private static HashMap<String, Double> getTowerUpgradeData(String towerName, int upgradeLevel) {
        HashMap<String, Double> upgrades = new HashMap<>();
        try {
            JSONObject upgradeObject = getUpgradeObject(towerName, upgradeLevel);

            upgrades.put("attackDmgMul", (double) upgradeObject.get("attackDmgMul"));
            upgrades.put("attackSpeedMul",  (double) upgradeObject.get("attackSpeedMul"));
            upgrades.put("attackRangeMul", (double) upgradeObject.get("attackRangeMul"));
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }

        return new HashMap<>(upgrades);
    }

}
