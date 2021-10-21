package com.mygdx.chalmersdefense.model.viruses;

import com.mygdx.chalmersdefense.model.path.IPath;


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
    StandardVirus(int health, IPath path) {
        super(health, path);
    }

    /**
     * Creates a virus object with specific values
     * @param health health of the virus
     * @param path  path to be used by virus
     * @param x   x-coordinate of virus
     * @param y   y-coordinate of virus
     * @param wayPointIndex  wayPointIndex virus should walk towards
     */
    StandardVirus(int health, IPath path, float x, float y, int wayPointIndex) {
        super(health, path, x,y, wayPointIndex);
    }

    @Override
    public void decreaseHealth(float damage) {
        super.decreaseHealth(damage);
        seeIfUpdateSpriteKey();
    }

}
