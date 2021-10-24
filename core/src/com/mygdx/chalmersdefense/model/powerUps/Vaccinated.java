package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Class representing Vaccinated powerup
 * 2021-10-15 Modified by Elin Forsberg: Implemented use of PowerUp factory and abstract PowerUp class
 */
final class Vaccinated extends PowerUp {
    private final List<IVirus> allViruses;        // List of viruses to apply powerUp to

    /**
     * Creates an instance of the power-up vaccinated
     *
     * @param allViruses reference to the virus list to do damage to
     */
    Vaccinated(List<IVirus> allViruses) {
        super(200 * 25, 380, 900);
        this.allViruses = allViruses;
    }

    @Override
    void addGraphicObject(List<IGenericMapObject> addGraphicsList) {
        addGraphicsList.add(GenericMapObjectFactory.createVaccinationStorm());
    }


    @Override
    public void decreaseTimer() {
        super.decreaseTimer();

        if (getCurrentTime() == 205) {
            damageVirus();
        }
    }

    //Damage all the viruses by 1 damage
    private void damageVirus() {
        for (IVirus virus : allViruses) {
            virus.decreaseHealth(1);
        }
    }

}
