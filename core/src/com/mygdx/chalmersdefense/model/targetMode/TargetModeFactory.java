package com.mygdx.chalmersdefense.model.targetMode;

public class TargetModeFactory {


    private Closest targetClosest;
    private First targetFirst;


    public TargetModeFactory() {
        targetClosest = new Closest();
        targetFirst = new First();
    }


    public TargetMode getClosestTarget() {
        return targetClosest;
    }
    public TargetMode getFirstTarget() {
        return targetFirst;
    }
}
