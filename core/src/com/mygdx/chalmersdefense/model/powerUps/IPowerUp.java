package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;

import java.util.List;

public interface IPowerUp {

    /**
     * Activates the power-up if the power-up can be used
     */
    void powerUpClicked(List<IGenericMapObject> addGraphicsList);

    /**
     * Decreases the power-up timer cooldowns
     */
    void decreaseTimer();

    /**
     * Return the active timer amount
     * @return active timer
     */
    int getTimer();

    /**
     * Return if the powerUp is active
     * @return if activated
     */
    boolean getIsActive();

    /**
     * Return the cost of the PowerUP
     * @return cost
     */
    int getCost();
}
