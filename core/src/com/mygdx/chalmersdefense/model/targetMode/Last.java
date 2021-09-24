package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.Virus;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Finds the virus that have travled the least distance
 */
public class Last implements ITargetMode {

    @Override
    public Virus getRightVirus(List<Virus> virusInRange, float towerX, float towerY) {
        Virus leastTrawledVirus = virusInRange.get(0);  // Need to have a virus to start comparing against

        for (Virus virus : virusInRange){
            if ((virus.getTotalDistanceTrawled() < leastTrawledVirus.getTotalDistanceTrawled())){
                leastTrawledVirus = virus;
            }
        }

        return leastTrawledVirus;
    }
}
