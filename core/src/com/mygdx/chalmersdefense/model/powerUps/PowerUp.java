package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.utilities.CountDownTimer;

import java.util.List;

abstract class PowerUp implements  IPowerUp{
    private boolean isActivated = false;
    private boolean canActivate = false;

    private final CountDownTimer cooldown;            // Cooldown of the powerUp
    private final CountDownTimer powerUpTimer;        // Lifetime of the powerUp


    PowerUp(int lengthOfCooldown, int lengthOfPowerUp){
        cooldown = new CountDownTimer(lengthOfCooldown, 0);
        powerUpTimer = new CountDownTimer(lengthOfPowerUp);

    }

    @Override
    public void powerUpClicked(List<IGenericMapObject> addGraphicsList){
        if(canActivate){
            canActivate = false;
            isActivated = true;
            activatePowerUp();
            addGraphicObject(addGraphicsList);
        }
    }

    abstract void addGraphicObject(List<IGenericMapObject> addGraphicsList);

    abstract void activatePowerUp();

    abstract void deActivatePowerUp();


    @Override
    public void decreaseTimer(){
        if (isActivated && powerUpTimer.haveReachedZero()) {
            deActivatePowerUp();
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

    int getCurrentTime(){
        return powerUpTimer.getCurrentCountTime();
    }
}
