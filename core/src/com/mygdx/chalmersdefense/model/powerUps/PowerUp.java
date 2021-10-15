package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.utilities.CountDownTimer;

import java.util.List;

abstract class PowerUp implements  IPowerUp{
    private boolean isActivated = false;              // If powerUp is activated
    private boolean canActivate = false;              // If powerUp can be activated

    private final CountDownTimer cooldown;            // Cooldown of the powerUp
    private final CountDownTimer powerUpTimer;        // Lifetime of the powerUp



    private final int cost;         // Cost of powerUp


    PowerUp(int lengthOfCooldown, int lengthOfPowerUp, int cost){
        cooldown = new CountDownTimer(lengthOfCooldown, 0);
        powerUpTimer = new CountDownTimer(lengthOfPowerUp);

        this.cost = cost;
    }

    @Override
    public void powerUpClicked(List<IGenericMapObject> addGraphicsList){
        if(canActivate){
            canActivate = false;
            isActivated = true;
            addGraphicObject(addGraphicsList);
        }
    }

    /**
     * Add graphicObject to the given list
     * @param addGraphicsList list to add object to
     */
    abstract void addGraphicObject(List<IGenericMapObject> addGraphicsList);

    @Override
    public void decreaseTimer(){
        if (isActivated && powerUpTimer.haveReachedZero()) {
            isActivated = false;
        }
        else if (!isActivated && !canActivate && cooldown.haveReachedZero()) {
            canActivate = true;
        }
    }

    @Override
    public int getTimer() {
        if(isActivated && !canActivate){
            return (powerUpTimer.getCurrentCountTime() * 5) / 1000;
        }
        else if(isActivated){
            return -1;
        }
        else if(!canActivate){
            return (cooldown.getCurrentCountTime() * 5) / 1000;
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean getIsActive() {
        return isActivated;
    }

    /**
     * Get current ime of powerUpTimer
     * @return current time
     */
    int getCurrentTime(){
        return powerUpTimer.getCurrentCountTime();
    }

    @Override
    public int getCost() {
        return cost;
    }
}
