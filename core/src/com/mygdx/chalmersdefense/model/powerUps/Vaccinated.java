package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing Vaccinated powerup
 * Modified 2021-10-15 by Elin Forsberg: Implemented use of PowerUp factory and abstract PowerUp class
 */
class Vaccinated extends PowerUp{
    private List<IVirus> allViruses;// Active timer

    Vaccinated(List<IVirus> allViruses) {
        super(1000, 380);
        this.allViruses = allViruses;
    }

    @Override
    void addGraphicObject(List<IGenericMapObject> addGraphicsList) {
        addGraphicsList.add(GenericMapObjectFactory.createVaccinationStorm());
    }

    @Override
    void activatePowerUp() {

    }

    @Override
    void deActivatePowerUp() {

    }

    @Override
    public void decreaseTimer(){
        super.decreaseTimer();

        if(getCurrentTime() == 205){
            damageVirus();
        }
    }

    private void damageVirus(){
        for (IVirus virus : allViruses){
            virus.decreaseHealth(1);
        }
    }

}
