package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.utilities.CountDownTimer;

import java.util.List;
import java.util.Random;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing CleanHands powerup, doubles attackspeed of towers
 */
public class CleanHands {

    private final CountDownTimer cooldownTimer = new CountDownTimer(750); // Cooldown timer
    private final CountDownTimer powerUpTimer = new CountDownTimer(500); // Power-up timer

    private boolean canBeUsed = true;   // If this powerup can be used at the moment
    private boolean powerUpIsActive = false;   // If this powerup is activated at the moment


    /**
     * Activates the power-up if the power-up can be used
     */
    public void activatePowerUp(List<IGenericMapObject> addGraphicsList){
        if (canBeUsed) {
            canBeUsed = false;
            powerUpIsActive = true;
            addGraphicObject(addGraphicsList);
        }
    }

    private void addGraphicObject(List<IGenericMapObject> addGraphicsList){
        Random rand = new Random();

        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, 90));
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, 10));
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, -120));
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, 160));

        for (int i = 0; i < 4; i++){
            addGraphicsList.add(GenericMapObjectFactory.createBubbles(rand.nextInt(1921), rand.nextInt(1081), rand.nextInt(361)));
        }
    }

    /**
     * Decreases the power-up timer cooldowns
     */
    public void decreaseTimer(){

        if (powerUpIsActive && powerUpTimer.haveReachedZero()){
            powerUpIsActive = false;
        }


        if (!canBeUsed && cooldownTimer.haveReachedZero() && !canBeUsed){
            canBeUsed = true;
        }
    }

    /**
     * Returns if the power-up is active at the moment
     * @return Status of power-up
     */
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
