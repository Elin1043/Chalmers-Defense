package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.utilities.CountDownTimer;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing Vaccinated powerup
 */
public class Vaccinated {
    //private int cooldownTimer = 750;    // Cooldown timer
    private final CountDownTimer cooldownTimer = new CountDownTimer(750);


    private boolean canBeUsed = true;   // If this powerup can be used att the moment

    public void activatePowerUp(List<IVirus> allViruses){
        if (canBeUsed) {
            canBeUsed = false;
            decreaseLife(allViruses);
        }
    }

    private void decreaseLife(List<IVirus> allViruses){
        for (IVirus virus : allViruses){
            virus.decreaseHealth(1);
        }
    }

    public void decreaseTimer(){
        if (!canBeUsed && cooldownTimer.haveReachedZero()){
            canBeUsed = true;
        }
    }


    /**
     * Return the active timer amount
     * @return active timer
     */
    public int getTimer() {
        if(!canBeUsed){
            return (cooldownTimer.getCurrentCountTime() * 5) / 1000;
        }
        else{
            return -1;
        }
    }
}
