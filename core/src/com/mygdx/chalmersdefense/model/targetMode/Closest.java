package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.Virus;
import com.mygdx.chalmersdefense.utilities.Calculate;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Elin Forsberg
 *
 * 2021-09-24 Modified by Joel Båtsman Hilmersson: Changed class to use ITargetMode interface
 *
 * Finds the virus that is the nearest to the tower
 */
class Closest implements ITargetMode{

    @Override
    public Virus getRightVirus(List<Virus> virusInRange, float towerX, float towerY) {
        Virus closestVirus = virusInRange.get(0);   // Need to hold the closes virus
        double closestDistance = Calculate.disBetweenPoints(towerX, towerY, closestVirus.getX(), closestVirus.getY());

        for (Virus virus : virusInRange){
            double rangeToVirus = Calculate.disBetweenPoints(towerX, towerY, virus.getX(), virus.getY());
            if (rangeToVirus < closestDistance){
                closestDistance = rangeToVirus;
                closestVirus = virus;
            }
        }

        return closestVirus;
    }
}
