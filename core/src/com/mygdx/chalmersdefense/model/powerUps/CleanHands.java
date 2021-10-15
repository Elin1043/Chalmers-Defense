package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.utilities.CountDownTimer;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing CleanHands powerup, doubles attackspeed of towers
 *
 * Modified 2021-10-15 by Elin Forsberg: Implemented use of PowerUp factory and abstract PowerUp class
 */
class CleanHands extends PowerUp{

    CleanHands() {
        super(500, 500);
    }

    @Override
    void addGraphicObject(List<IGenericMapObject> addGraphicsList) {
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, 90));
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, 10));
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, -120));
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, 160));
    }

}
