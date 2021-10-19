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
     * @param path   The path to follow
     */
    BossVirus( Path path) {
        super(50, path);
    }
}
