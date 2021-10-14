package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.utilities.CountDownTimer;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing CleanHands powerup, doubles attackspeed of towers
 */
public class CleanHands {

    private final CountDownTimer cooldownTimer = new CountDownTimer(750); // Cooldown timer
    private final CountDownTimer powerUpTimer = new CountDownTimer(500); // Power-up timer

    private boolean canBeUsed = true;   // If this powerup can be used at the moment
    private boolean powerUpIsActive = false;   // If this powerup is activated at the moment



    public void activatePowerUp(){
        if (canBeUsed) {
            canBeUsed = false;
            powerUpIsActive = true;
        }
    }

    public void decreaseTimer(){

        if (powerUpIsActive && powerUpTimer.haveReachedZero()){
            powerUpIsActive = false;
        }


        if (!canBeUsed && cooldownTimer.haveReachedZero() && !canBeUsed){
            canBeUsed = true;
        }
    }

    public boolean isActive(){
        return powerUpIsActive;
    }

    /**
     * Return the active timer amount
     * @return active timer
     */
    public int getTimer() {
        if(powerUpIsActive && !canBeUsed){
            return (powerUpTimer.getCurrentCountTime() * 5) / 1000;
        }
        else if(powerUpIsActive){
            return -1;
        }
        else if(!canBeUsed){
            return (cooldownTimer.getCurrentCountTime() * 5) / 1000;
        }
        else{
            return -1;
        }
    }
}
