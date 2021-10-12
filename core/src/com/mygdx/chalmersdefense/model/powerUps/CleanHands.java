package com.mygdx.chalmersdefense.model.powerUps;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing CleanHands powerup, doubles attackspeed of towers
 */
public class CleanHands {
    private int cooldownTimer = 750;    // Cooldown timer
    private int powerUpTimer = 500;    // Cooldown timer
    private boolean canBeUsed = true;   // If this powerup can be used att the moment
    private boolean powerUpIsActive = false;   // If this powerup can be used att the moment



    public void activatePowerUp(){
        if (canBeUsed) {
            canBeUsed = false;
            powerUpIsActive = true;
        }
    }

    public void decreaseTimer(){

        if (powerUpTimer > 0 && powerUpIsActive){
            powerUpTimer--;
        } else {
            powerUpTimer = 500;
            powerUpIsActive = false;
        }


        if (cooldownTimer > 0 && !canBeUsed){
            cooldownTimer--;
        } else {
            canBeUsed = true;
            cooldownTimer = 750;
        }
    }

    public boolean isActive(){
        return powerUpIsActive;
    }
}
