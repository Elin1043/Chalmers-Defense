package com.mygdx.chalmersdefense.model.powerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;

import java.util.List;
import java.util.Random;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing CleanHands powerup, doubles attackspeed of towers
 *
 * Modified 2021-10-15 by Elin Forsberg: Implemented use of PowerUp factory and abstract PowerUp class
 */
class CleanHands extends PowerUp{

    CleanHands() {
        super(500, 500, 300);
    }

    @Override
    void addGraphicObject(List<IGenericMapObject> addGraphicsList) {
        Random rand = new Random();

        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, 90));
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, 10));
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, -120));
        addGraphicsList.add(GenericMapObjectFactory.createBubbles(700, 400, 160));

        for (int i = 0; i < 4; i++){
            addGraphicsList.add(GenericMapObjectFactory.createBubbles(rand.nextInt(1921), rand.nextInt(1081), rand.nextInt(361)));
        }
    }

}
