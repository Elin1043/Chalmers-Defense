package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.utilities.CountDownTimer;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing CleanHands powerup, doubles attackspeed of towers
 */
public class CleanHands {
    //private int cooldownTimer = 750;    // Cooldown timer
    private final CountDownTimer cooldownTimer = new CountDownTimer(750);

    //private int powerUpTimer = 500;    // Cooldown timer
    private final CountDownTimer powerUpTimer = new CountDownTimer(500);

    private boolean canBeUsed = true;   // If this powerup can be used att the moment
    private boolean powerUpIsActive = false;   // If this powerup can be used att the moment



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
}
