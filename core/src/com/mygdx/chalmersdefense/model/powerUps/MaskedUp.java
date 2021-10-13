package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.utilities.CountDownTimer;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing MaskedUp powerup, increases range of towers
 *
 * 2021-10-13 Modified by Joel Båtsman Hilmersson: Changed class to use CountDownTimer <br>
 */
public class MaskedUp {


    //private int cooldown = 0;    // Cooldown of the powerUp
    private final CountDownTimer cooldown = new CountDownTimer(500);

    //private int powerUpTimer = 500;    // Lifetime of the powerUp
    private final CountDownTimer powerUpTimer = new CountDownTimer(500);

    private List<ITower> allTowers;


    private boolean isActivated = false;
    private boolean canActivate = false;


    public void powerUpClicked(List<ITower> allTowers){
        this.allTowers = allTowers;
        if(canActivate){
            activatePowerUp();
            //powerUpTimer = 500;
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

        if (isActivated && powerUpTimer.haveReachedZero()) {
            deActivatePowerUp();
            isActivated = false;
        }
        else if (!canActivate && cooldown.haveReachedZero()) {
            canActivate = true;
        }

    }

    /**
     * Return the active timer amount
     * @return active timer
     */
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

    /**
     * Return if the powerUp is active
     * @return if activated
     */
    public boolean getIsActive() {
        return isActivated;
    }
}
