package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.towers.ITower;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing MaskedUp powerup, increases range of towers
 */
public class MaskedUp {


    private int cooldown = 0;    // Cooldown of the powerUp
    private int powerUpTimer = 500;    // Lifetime of the powerUp
    private List<ITower> allTowers;


    private boolean isActivated = false;
    private boolean canActivate = false;


    public void powerUpClicked(List<ITower> allTowers){
        this.allTowers = allTowers;
        if(canActivate){
            activatePowerUp();
            powerUpTimer = 500;
            canActivate = false;
            isActivated = true;
        }
    }

    private void activatePowerUp(){
        for (ITower tower: allTowers) {
            tower.powerUpTower(true);
        }
    }

    private void deActivatePowerUp(){
        for (ITower tower: allTowers) {
            tower.powerUpTower(false);
        }
    }

    public void decreaseTimer(){
        if(isActivated){
            if (powerUpTimer <= 0) {
                deActivatePowerUp();
                isActivated = false;
                cooldown = 500;
            } else {
                powerUpTimer --;
            }
        }
        else{
            if (cooldown <= 0) {
                canActivate = true;
            } else {
                cooldown --;
            }
        }

    }

    /**
     * Return the active timer amount
     * @return active timer
     */
    public int getTimer() {
        if(isActivated && !canActivate){
            return (powerUpTimer * 5) / 1000;
        }
        else if(isActivated ){
            return -1;
        }
        else if(!isActivated && !canActivate){
            return (cooldown * 5) / 1000;
        }
        else{
            return -1;
        }
    }

    /**
     * Return if the powerUp is active
     * @return if activated
     */
    public boolean getIsActive() {
        return isActivated;
    }
}
