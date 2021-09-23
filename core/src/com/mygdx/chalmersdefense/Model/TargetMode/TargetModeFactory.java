package com.mygdx.chalmersdefense.Model.TargetMode;

public class TargetModeFactory {


    private Closest targetClosest;


    public TargetModeFactory() {
        targetClosest = new Closest();
    }


    public TargetMode getClosestTarget() {
        return targetClosest;
    }
}
