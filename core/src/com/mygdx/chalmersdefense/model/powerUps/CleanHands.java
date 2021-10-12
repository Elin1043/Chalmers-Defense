package com.mygdx.chalmersdefense.model.powerUps;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing CleanHands powerup, doubles attackspeed of towers
 */
public class CleanHands {
    private int coolDownTimer = 750;    // Cool down timer
    private int powerUpTimer = 500;    // Cool down timer
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


        if (coolDownTimer > 0 && !canBeUsed){
            coolDownTimer--;
        } else {
            canBeUsed = true;
            coolDownTimer = 750;
        }
    }

    public boolean isActive(){
        return powerUpIsActive;
    }
}