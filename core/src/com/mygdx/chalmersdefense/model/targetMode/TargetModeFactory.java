package com.mygdx.chalmersdefense.model.targetMode;

public abstract class TargetModeFactory {


    private final static Closest targetClosest = new Closest();
    private final static First targetFirst = new First();

    public static TargetMode getClosestTarget() { return targetClosest; }
    public static TargetMode getFirstTarget() {
        return targetFirst;
    }
}
