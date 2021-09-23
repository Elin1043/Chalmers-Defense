package com.mygdx.chalmersdefense.Model.TargetMethods;

import com.mygdx.chalmersdefense.Model.Virus;

import java.util.ArrayList;

public class TargetClosest extends TargetMethod{
    public TargetClosest(){

    }
    @Override
    public Virus getTarget(ArrayList<Virus> viruses, int posX, int posY, float range) {
        double rangeToClosest = 0;
        Virus closestVirus;

        if(viruses.size() > 0){
            ArrayList<Virus> inRange = new ArrayList<Virus>();
            for(Virus c: viruses){
                if(isWithinRange(c,posX,posY,range)){
                    inRange.add(c);
                }
            }
            if(inRange.size() <= 0){
                return null;
            }

            closestVirus = inRange.get(0);
            rangeToClosest = CalculateDistance.distanceFromVirus(posX,posY, closestVirus.getX(),closestVirus.getY());

            for (Virus virus:inRange){
                double rangeToCurrent = CalculateDistance.distanceFromVirus(posX,posY, virus.getX(), virus.getY());
                if (rangeToCurrent < rangeToClosest){
                    rangeToClosest = rangeToCurrent;
                    closestVirus = virus;
                }
            }

            return closestVirus;
        }
        return null;
    }
}
