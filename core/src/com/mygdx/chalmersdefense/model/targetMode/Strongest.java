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
        IVirus strongestVirus = virusInRange.get(0);  // Need to have a virus to start comparing against

        for (IVirus virus : virusInRange) {
            if ((virus.getLifeDecreaseAmount() > strongestVirus.getLifeDecreaseAmount())) {
                strongestVirus = virus;
            } else if (virus.getLifeDecreaseAmount() == strongestVirus.getLifeDecreaseAmount()){
                strongestVirus = getMostTravledVirus(strongestVirus, virus);
            }
        }

        return strongestVirus;
    }

    private IVirus getMostTravledVirus(IVirus strongestVirus, IVirus virus) {
        if (virus.getTotalDistanceTraveled() > strongestVirus.getTotalDistanceTraveled()){
            strongestVirus = virus;
        }
        return strongestVirus;
    }
}
