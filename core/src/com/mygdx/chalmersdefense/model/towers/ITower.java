package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.HashMap;
import java.util.List;

/**
 * @author Elin Forsberg
 * Interface for towers
 */
public interface ITower extends IMapObject {

    /**
     * Update all the projectiles
     *
     * @param projectilesList list of projectiles
     * @param newAgle         new angle of projectile
     * @param hasTarget       if projectile has a target
     */
    void update(List<IProjectile> projectilesList, float newAgle, boolean hasTarget);

    /**
     * Upgrades the tower based on given HashMap with upgrade values
     *
     * @param upgrades a HashMap with upgrade values.
     */
    void upgradeTower(HashMap<String, Double> upgrades);

    /**
     * PowerUp the towers based on given parameter
     * @param maskedUp if powerup is MaskedUp powerup
     */
    void powerUpTower(boolean maskedUp);

    /**
     * Get the upgrade level of tower
     *
     * @return upgrade level
     */
    int getUpgradeLevel();

    /**
     * Gets the cost of tower
     *
     * @return cost of tower
     */
    int getCost();

    /**
     * Gets if tower is colliding with something else
     *
     * @return tower collision
     */
    boolean canRemove();

    /**
     * Sets if tower is colliding with something else
     *
     * @param set if tower is colliding
     */
    void setIfCanRemove(boolean set);

    /**
     * Gets name of tower
     *
     * @return name of tower
     */
    String getName();

    /**
     * Sets the position of the tower
     *
     * @param x The X-coordinate to set
     * @param y The Y-coordinate to set
     */
    void setPos(float x, float y);

    /**
     * Gets the range of the tower
     *
     * @return range of tower
     */
    int getRange();

    /**
     * Gets the current targetMode of tower
     *
     * @return current targetMode
     */
    ITargetMode getCurrentTargetMode();

    /**
     * Gets if tower is placed
     *
     * @return if placed
     */
    boolean isPlaced();

    /**
     * Sets that tower is placed
     */
    void placeTower();

    /**
     * Remove the tower from list
     * @param towersList to remove from
     */
    void remove(List<ITower> towersList);

    /**
     * Change the targetMode of the tower
     * @param goRight which way in array to go
     */
    void changeTargetMode(boolean goRight);
}
