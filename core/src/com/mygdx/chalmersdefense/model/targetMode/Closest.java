package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.Virus;
import com.mygdx.chalmersdefense.utilities.Calculate;


import java.util.ArrayList;
import java.util.List;

public class Closest extends TargetMode{



    @Override
    public Virus getTarget(List<Virus> viruses, float x, float y, double range){
        double disToClosest;
        Virus closestVirus;

        if(viruses.size() > 0){
            List<Virus> inRange = new ArrayList<>();
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
