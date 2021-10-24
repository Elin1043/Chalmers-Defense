package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.modelUtilities.CountDownTimer;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing an abstract power-up class holding common functions.
 */
abstract class PowerUp implements IPowerUp {
    private boolean isActivated = false;              // If powerUp is activated
    private boolean canActivate = true;              // If powerUp can be activated

    private CountDownTimer cooldown;            // Cooldown of the powerUp
    private CountDownTimer powerUpTimer;        // Lifetime of the powerUp

    private final int cost;             // Cost of powerUp
    private final int lengthOfCooldown; // The final length of cooldown timer. Used when resetting timers
    private final int lengthOfPowerUp;  // The final length of power-up timer. Used when resetting timers

    /**
     * Creates an instance of the power-up
     *
     * @param lengthOfCooldown the length of power-up cool down
     * @param lengthOfPowerUp  the length of active power-up time
     * @param cost             the cost of the power-up
     */
    PowerUp(int lengthOfCooldown, int lengthOfPowerUp, int cost) {
        cooldown = new CountDownTimer(lengthOfCooldown);
        powerUpTimer = new CountDownTimer(lengthOfPowerUp);
        this.lengthOfCooldown = lengthOfCooldown;
        this.lengthOfPowerUp = lengthOfPowerUp;
        this.cost = cost;
    }

    @Override
    public void powerUpClicked(List<IGenericMapObject> addGraphicsList) {
        if (canActivate) {
            canActivate = false;
            isActivated = true;
            addGraphicObject(addGraphicsList);
        }
    }

    /**
     * Add graphicObject to the given list
     *
     * @param addGraphicsList list to add object to
     */
    abstract void addGraphicObject(List<IGenericMapObject> addGraphicsList);

    @Override
    public void decreaseTimer() {
        if (isActivated && powerUpTimer.haveReachedZero()) {
            isActivated = false;

        } else if (!isActivated && !canActivate && cooldown.haveReachedZero()) {
            canActivate = true;
        }
    }

    @Override
    public int getTimer() {
        if (isActivated && !canActivate) {
            return (powerUpTimer.getCurrentCountTime() * 5) / 1000;
        } else if (!canActivate) {
            return (cooldown.getCurrentCountTime() * 5) / 1000;
        } else {
            return -1;
        }
    }

    @Override
    public boolean getIsActive() {
        return isActivated;
    }

    /**
     * Get current time of powerUpTimer
     *
     * @return current time
     */
    int getCurrentTime() {
        return powerUpTimer.getCurrentCountTime();
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void resetPowerUp() {
        cooldown = new CountDownTimer(lengthOfCooldown);
        powerUpTimer = new CountDownTimer(lengthOfPowerUp);
        isActivated = false;
        canActivate = true;
    }
}
