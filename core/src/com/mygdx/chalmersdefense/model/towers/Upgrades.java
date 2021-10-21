package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.utilities.IParseJSON;
import com.mygdx.chalmersdefense.utilities.JSONParserWrapper;

import java.util.HashMap;

/**
 * @author Daniel Persson
 * A class for storing and applying upgrades to towers.
 */
public abstract class Upgrades {
    private static final int MAX_UPGRADES = 3;                     // Current max upgrade level
    private static final IParseJSON jsonParser = new JSONParserWrapper("UpgradeData.json");

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
        String title;
        try {
            title = jsonParser.startParser().navThroughJSON(towerName).getByIndex(upgradeLevel-1).getString("title");
        } catch (NullPointerException | IllegalArgumentException exception) {
            title = "";
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
        String desc;
        try {
            desc = jsonParser.startParser().navThroughJSON(towerName).getByIndex(upgradeLevel-1).getString("desc");
        } catch (NullPointerException | IllegalArgumentException exception) {
            desc = "";
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
    public static Integer getTowerUpgradePrice(String towerName, int upgradeLevel) {
        Integer price;
        try {
            price = jsonParser.startParser().navThroughJSON(towerName).getByIndex(upgradeLevel-1).getInteger("price");
        } catch (NullPointerException | IllegalArgumentException exception) {
            price = 0;
        }
        return price;
    }

    /**
     * Generates a HashMap with appropriate upgrade data from a JSON object and returns a copy of the HashMap.
     *
     * @param towerName    to get upgrades from
     * @param upgradeLevel upgrade level to get upgrades from
     * @return a HashMap with upgrade data.
     */
    private static HashMap<String, Double> getTowerUpgradeData(String towerName, int upgradeLevel) {
        String[] upgradeAttributes = {"attackDmgMul", "attackSpeedMul", "attackRangeMul"};
        HashMap<String, Double> upgrades = new HashMap<>();
        try {
            upgrades = jsonParser.startParser().navThroughJSON(towerName).getByIndex(upgradeLevel-1).getDoubleHashMap(upgradeAttributes);
        } catch (IllegalArgumentException exception) {
            // If retrieving of upgrade data fails set all values to 1 (Multiplier = 1 produces no modification to towers).
            for (String attr : upgradeAttributes) {
                upgrades.put(attr, 1D);
            }
        }
        return new HashMap<>(upgrades);
    }

}
