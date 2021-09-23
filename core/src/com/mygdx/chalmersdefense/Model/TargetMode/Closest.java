package com.mygdx.chalmersdefense.Model.TargetMode;

import com.mygdx.chalmersdefense.Model.Virus;

import java.util.ArrayList;

public abstract class Closest extends TargetMode{



    @Override
    public Virus getTarget(ArrayList<Virus> viruses, float x, float y, double range){
        double disToClosest;
        Virus closestVirus;

        if(viruses.size() > 0){
            ArrayList<Virus> inRange = new ArrayList<>();
            for(Virus c: viruses){
                if(isWithinRange(c,x, y,range)){
                    inRange.add(c);
                }
            }
            if(inRange.size() <= 0){
                return null;
            }

            closestVirus = inRange.get(0);
            disToClosest = Calculate.disBetweenPoints(x,y, closestVirus.getX(), closestVirus.getX());

            for (Virus virus:inRange){
                double rangeToCurrent = Calculate.disBetweenPoints(x,y, virus.getX(), virus.getY());
                if (rangeToCurrent < disToClosest){
                    disToClosest = rangeToCurrent;
                    closestVirus = virus;
                }
            }

            return closestVirus;
        }
        return null;
    }

}
