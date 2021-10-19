package com.mygdx.chalmersdefense.model.viruses;

import com.mygdx.chalmersdefense.model.path.Path;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing boss virus
 */
final public class BossVirus extends Virus{
    /**
     * Creates Virus object
     *
     * @param path The path to follow
     */
    BossVirus(Path path) {
        super(50, path);
    }

    @Override
    void slowDownEffect(float slowdown){
        float newSlowdown = slowdown;
        newSlowdown *= (1 + (1 - slowdown));
        super.slowDownEffect(newSlowdown);
    }

    @Override
    double getTotalVirusSpeed() {
        return 0.8 * getSlowdown();
    }


}
