package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.towers.ITower;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing MaskedUp powerup, increases range of towers
 *
 * 2021-10-13 Modified by Joel Båtsman Hilmersson: Changed class to use CountDownTimer <br>
 * Modified 2021-10-15 by Elin Forsberg: Implemented use of PowerUp factory and abstract PowerUp class
 */
class MaskedUp extends PowerUp{
    private List<ITower> allTowers;

    MaskedUp(List<ITower> allTowers) {
        super(500, 500, 100);
        this.allTowers = allTowers;
    }

    @Override
    void addGraphicObject(List<IGenericMapObject> addGraphicsList){
        addGraphicsList.add(GenericMapObjectFactory.createMaskedUpSmurf(-500, 500, 0));
    }

    @Override
    public void powerUpClicked(List<IGenericMapObject> addGraphicsList){
        super.powerUpClicked(addGraphicsList);
        if(getIsActive()){
            activatePowerUp();
        }
    }

    @Override
    public void decreaseTimer(){
        super.decreaseTimer();
        if (getIsActive() && getCurrentTime() <= 0) {
            deActivatePowerUp();
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