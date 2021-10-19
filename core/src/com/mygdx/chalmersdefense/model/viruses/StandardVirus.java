package com.mygdx.chalmersdefense.model.viruses;

import com.mygdx.chalmersdefense.model.path.Path;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing standard virus
 *
 * 2021-10-19 Modified by Elin: implemented the class into the application
 */
final class StandardVirus extends Virus{

    /**
     * Creates Virus object
     *
     * @param health Amount of health the virus start with
     * @param path   The path to follow
     */
    StandardVirus(int health, Path path) {
        super(health, path);
    }

    @Override
    public void decreaseHealth(float damage) {
        super.decreaseHealth(damage);
        seeIfUpdateSpriteKey();
    }

}
