package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.towers.ITower;

import java.util.List;
import java.util.Random;

/**
 * @author Elin Forsberg
 * Class representing MaskedUp powerup, increases range of towers
 *
 * 2021-10-13 Modified by Joel Båtsman Hilmersson: Changed class to use CountDownTimer <br>
 * 2021-10-15 Modified by Elin Forsberg: Implemented use of PowerUp factory and abstract PowerUp class <br>
 */
final class MaskedUp extends PowerUp{
    private final List<ITower> allTowers;

    MaskedUp(List<ITower> allTowers) {
        super(500, 500, 100);
        this.allTowers = allTowers;
    }

    @Override
    public void powerUpClicked(List<IGenericMapObject> addGraphicsList){
        super.powerUpClicked(addGraphicsList);
        if(getIsActive()){
            activatePowerUp();
        }
    }

    @Override
    public void decreaseTimer() {
        super.decreaseTimer();
        if (getIsActive() && getCurrentTime() <= 0) {
            deActivatePowerUp();
        }
    }

    @Override
    void addGraphicObject(List<IGenericMapObject> addGraphicsList){
        Random rand = new Random();

        addGraphicsList.add(GenericMapObjectFactory.createMaskedUpSmurf(-500, 500, 0));

        for (int i = 0; i < 7; i++) {
            addGraphicsList.add(GenericMapObjectFactory.createHappyMask(-100, rand.nextInt(1501) - 200, rand.nextInt(91) - 45));
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
}

