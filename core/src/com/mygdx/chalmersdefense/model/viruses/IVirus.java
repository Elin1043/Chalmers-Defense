package com.mygdx.chalmersdefense.model.viruses;

import com.mygdx.chalmersdefense.model.IMapObject;

/**
 * @author Joel Båtsman Hilmersson
 * Interface for virus
 */
public interface IVirus extends IMapObject {

    /**
     * Update all the viruses
     */
    void update();

    /**
     * Returns if the virus is dead, aka health = 0
     * @return if virus is dead
     */
    boolean isDead();

    /**
     * Return the virus health to later decrease players health with
     * @return the virus health
     */
    int getLifeDecreaseAmount();

    /**
     * Return the total distance traveled by the virus
     * @return
     */
    float getTotalDistanceTraveled();

    /**
     * Decrease the health of the virus
     */
    void decreaseHealth();

}
