package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing Vaccinated powerup
 */
public class Vaccinated {
    private int cooldownTimer = 750;    // Cooldown timer
    private boolean canBeUsed = true;   // If this powerup can be used att the moment

    public void activatePowerUp(List<IVirus> allViruses){
        System.out.println(canBeUsed);
        if (canBeUsed) {
            canBeUsed = false;
            decreaseLife(allViruses);
        }
    }

    private void decreaseLife(List<IVirus> allViruses){
        for (IVirus virus : allViruses){
            virus.decreaseHealth();
        }
    }

    public void decreaseTimer(){
        if (cooldownTimer > 0 && !canBeUsed){
            cooldownTimer--;
        } else {
            canBeUsed = true;
            cooldownTimer = 750;
        }
    }
}
