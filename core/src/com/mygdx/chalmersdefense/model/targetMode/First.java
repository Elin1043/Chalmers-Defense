package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.Virus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elin Forsberg
 *
 * 2021-09-24 Modified by Joel Båtsman Hilmersson: Changed class to use ITargetMode interface
 *
 * Finds the virus that have travled the furthest distance
 */
public class First implements ITargetMode{

    @Override
    public Virus getRightVirus(List<Virus> virusInRange, float towerX, float towerY) {
        Virus furthestVirus = virusInRange.get(0);  // Need to have a virus to start comparing against

        for (Virus virus : virusInRange){
            if ((virus.getTotalDistanceTrawled() > furthestVirus.getTotalDistanceTrawled())){
                furthestVirus = virus;
            }
        }

        return furthestVirus;
    }
}
