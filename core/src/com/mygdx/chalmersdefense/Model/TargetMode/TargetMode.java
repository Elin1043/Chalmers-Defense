package com.mygdx.chalmersdefense.Model.TargetMode;

import com.mygdx.chalmersdefense.Model.Virus;


import java.util.List;

public abstract class TargetMode {

    public abstract Virus getTarget(List<Virus> viruses, float x, float y, double range);

    public boolean isWithinRange(Virus virus, float x, float y, double range){
        return Calculate.disBetweenPoints(x,y, virus.getX() ,virus.getY()) < range*range;
    }
}
