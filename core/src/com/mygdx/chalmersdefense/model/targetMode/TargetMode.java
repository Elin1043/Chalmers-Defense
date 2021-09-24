package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.Virus;
import com.mygdx.chalmersdefense.utilities.Calculate;


import java.util.List;

public abstract class TargetMode {

    public abstract Virus getTarget(List<Virus> viruses, float x, float y, double range);

    public boolean isWithinRange(Virus virus, float x, float y, double range){
        return Calculate.disBetweenPoints(x,y, virus.getX() ,virus.getY()) < range*range;
    }
}
