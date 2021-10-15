package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.utilities.CountDownTimer;

import java.util.List;
import java.util.Random;

/**
 * @author Elin Forsberg
 * Class representing MaskedUp powerup, increases range of towers
 *
 * 2021-10-13 Modified by Joel Båtsman Hilmersson: Changed class to use CountDownTimer <br>
 */
public class MaskedUp {


    private final CountDownTimer cooldown = new CountDownTimer(500, 0); // Cooldown of the powerUp
    private final CountDownTimer powerUpTimer = new CountDownTimer(500);        // Lifetime of the powerUp

    private List<ITower> allTowers;


    private boolean isActivated = false;
    private boolean canActivate = false;


    /**
     * Activates the power-up if the power-up can be used
     */
    public void powerUpClicked(List<ITower> allTowers, List<IGenericMapObject> addGraphicsList){
        this.allTowers = allTowers;
        if(canActivate){
            canActivate = false;
            isActivated = true;
            activatePowerUp();
            addGraphicObject(addGraphicsList);
        }
    }

    private void addGraphicObject(List<IGenericMapObject> addGraphicsList){
        Random rand = new Random();

        addGraphicsList.add(GenericMapObjectFactory.createMaskedUpSmurf(-500, 500, 0));

        for (int i = 0; i < 7; i++) {
            addGraphicsList.add(GenericMapObjectFactory.createHappyMask(-50, rand.nextInt(1501) - 200, rand.nextInt(91) - 45));
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

    /**
     * Decreases the power-up timer cooldowns
     */
    public void decreaseTimer(){

        if (isActivated && powerUpTimer.haveReachedZero()) {
            deActivatePowerUp();
            isActivated = false;
        }
        else if (!isActivated && !canActivate && cooldown.haveReachedZero()) {
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
