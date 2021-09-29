package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.viruses.Virus;

import java.util.List;

/**
 * @author Elin Forsberg
 * Finds the virus that have travled the furthest distance
 *
 * 2021-09-24 Modified by Joel Båtsman Hilmersson: Changed class to use ITargetMode interface
 *
 */
class First implements ITargetMode{

    @Override
    public Virus getRightVirus(List<Virus> virusInRange, float towerX, float towerY) {
        Virus firstVirus = virusInRange.get(0);  // Need to have a virus to start comparing against

        for (Virus virus : virusInRange){
            if ((virus.getTotalDistanceTravled() > firstVirus.getTotalDistanceTravled())){
                firstVirus = virus;
            }
        }

        return firstVirus;
    }
}
