package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

/**
 * @author Elin Forsberg
 * Finds the virus that have travled the furthest distance
 * <p>
 * 2021-09-24 Modified by Joel Båtsman Hilmersson: Changed class to use ITargetMode interface <br>
 */
class First implements ITargetMode {

    @Override
    public IVirus getCorrectVirus(List<IVirus> virusInRange, float towerX, float towerY) {
        IVirus firstVirus = virusInRange.get(0);  // Need to have a virus to start comparing against

        for (IVirus virus : virusInRange) {
            if ((virus.getTotalDistanceTraveled() > firstVirus.getTotalDistanceTraveled())) {
                firstVirus = virus;
            }
        }

        return firstVirus;
    }
}
