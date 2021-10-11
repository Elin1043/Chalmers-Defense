package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Finds the stronges virus of the virus in the given list
 */
class Strongest implements ITargetMode {

    @Override
    public IVirus getCorrectVirus(List<IVirus> virusInRange, float towerX, float towerY) {
        return null;
    }
}
