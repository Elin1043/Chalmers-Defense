package com.mygdx.chalmersdefense.model.towers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Daniel Persson
 * A class for storing and applying upgrades to towers.
 *
 */
public class Upgrades {
    private final JSONParser parser = new JSONParser();
    private JSONObject mainObject;
    private final int MAX_UPGRADES = 3;

    public Upgrades() {
        try {
            mainObject = (JSONObject) parser.parse(new FileReader("core/assets/UpgradeData.json"));
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Main method for upgrading a tower.
     * @param tower tower to be upgraded
    */
    public void upgradeTower(Tower tower) {
        // If tower is max upgraded dont upgrade
        if (tower.getUpgradeLevel() >= MAX_UPGRADES) return;

        HashMap<String, Long> upgrades = getTowerUpgradeData(tower, tower.getUpgradeLevel());

        tower.upgradeTower(upgrades);
    }

    /**
     * Gets title of tower upgrade from database with regards for upgrade level.
     * @param tower used to get towers name
     * @param upgradeLevel level up upgrade to get title of
     * @return a String with towers upgrade title depending on upgrade level.
     */
    public String getTowerUpgradeTitle(Tower tower, int upgradeLevel) {
        try {
            JSONArray towerUpgradeArray = (JSONArray) mainObject.get(tower.getName());
            JSONObject upgradeObject = (JSONObject) towerUpgradeArray.get(upgradeLevel - 1);
            return upgradeObject.get("title").toString();
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        return "";
    }

    /**
     * Gets description of tower upgrade from database with regards for upgrade level.
     * @param tower used to get towers name
     * @param upgradeLevel level up upgrade to get description of
     * @return a String with towers upgrade description depending on upgrade level.
     */
    public String getTowerUpgradeDesc(Tower tower, int upgradeLevel) {
        try {
            JSONArray towerUpgradeArray = (JSONArray) mainObject.get(tower.getName());
            JSONObject upgradeObject = (JSONObject) towerUpgradeArray.get(upgradeLevel - 1);
            return upgradeObject.get("desc").toString();
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        return "";
    }

    /**
     * Generates a HashMap with appropriate upgrade data from a JSON object and returns a copy of the HashMap.
     * @param tower tower to get upgrades from
     * @param upgradeLevel upgrade level to get upgrades from
     * @return a HashMap with upgrade data.
     */
    private HashMap<String, Long> getTowerUpgradeData(Tower tower, int upgradeLevel) {
        HashMap<String, Long> upgrades = new HashMap<>();
        try {
            JSONArray towerUpgradeArray = (JSONArray) mainObject.get(tower.getName());
            JSONObject upgradeObject = (JSONObject) towerUpgradeArray.get(upgradeLevel - 1);

            upgrades.put("attackDmgMul", (Long) upgradeObject.get("attackDmgMul"));
            upgrades.put("attackSpeedMul", (Long) upgradeObject.get("attackDmgMul"));
            upgrades.put("attackRangeMul", (Long) upgradeObject.get("attackDmgMul"));
        } catch (NullPointerException exception) {
            System.out.println(exception.getMessage());
        }

        return new HashMap<>(upgrades);
    }

}
