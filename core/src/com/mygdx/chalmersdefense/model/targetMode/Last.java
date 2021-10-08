package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 * Finds the virus that have travled the least distance
 */
class Last implements ITargetMode {

    @Override
    public IVirus getCorrectVirus(List<IVirus> virusInRange, float towerX, float towerY) {
        IVirus leastTraveledVirus = virusInRange.get(0);  // Need to have a virus to start comparing against

        for (IVirus virus : virusInRange) {
            if ((virus.getTotalDistanceTraveled() < leastTraveledVirus.getTotalDistanceTraveled())) {
                leastTraveledVirus = virus;
            }
        }

        return leastTraveledVirus;
    }
}
