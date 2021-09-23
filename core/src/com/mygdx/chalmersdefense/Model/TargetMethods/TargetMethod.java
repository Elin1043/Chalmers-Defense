package com.mygdx.chalmersdefense.Model.TargetMethods;

import com.mygdx.chalmersdefense.Model.Virus;

import java.util.ArrayList;

public abstract class TargetMethod {
    public void TargetingMethod() {
    }
    public abstract Virus getTarget(ArrayList<Virus> viruses, int posX, int posY, float range);

    public boolean isWithinRange(Virus virus, int posX, int posY, float range){
        //return PointCalculations.distanceBetweenNoSqrt(towerPosition, creep.getCenter()) < range*range;
        return true;
    }
}
