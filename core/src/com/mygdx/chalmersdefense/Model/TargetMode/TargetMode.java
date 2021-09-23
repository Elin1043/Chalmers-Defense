package com.mygdx.chalmersdefense.Model.TargetMode;

import com.mygdx.chalmersdefense.Model.Virus;

import java.util.ArrayList;

public abstract class TargetMode {

    public abstract Virus getTarget(ArrayList<Virus> viruses, float x, float y, double range);

    public boolean isWithinRange(Virus virus, float x, float y, double range){
        return Calculate.disBetweenPoints(x,y, virus.getX() ,virus.getY()) < range*range;
    }
}
