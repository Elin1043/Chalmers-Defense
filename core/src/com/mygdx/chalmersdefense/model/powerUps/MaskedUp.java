package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;

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

    /**
     * Creates an instance of the power up Masked-Up
     */
    MaskedUp() {
        super(500, 500, 100);
    }

    @Override
    void addGraphicObject(List<IGenericMapObject> addGraphicsList){
        Random rand = new Random();

        addGraphicsList.add(GenericMapObjectFactory.createMaskedUpSmurf(-500, 500, 0));

        for (int i = 0; i < 7; i++) {
            addGraphicsList.add(GenericMapObjectFactory.createHappyMask(-100, rand.nextInt(1501) - 200, rand.nextInt(91) - 45));
        }
    }

}

