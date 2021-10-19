package com.mygdx.chalmersdefense.model.viruses;

import com.mygdx.chalmersdefense.model.path.Path;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing boss virus
 */
public class BossVirus extends Virus{
    /**
     * Creates Virus object
     *
     * @param health Amount of health the virus start with
     * @param path   The path to follow
     */
    BossVirus(int health, Path path) {
        super(health, path);
    }
}
