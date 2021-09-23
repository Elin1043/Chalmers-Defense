package com.mygdx.chalmersdefense.Model.TargetMode;

import com.badlogic.gdx.math.MathUtils;


public abstract class Calculate {

    private static MathUtils utils;

    //Jämför koordinaterna den får in med tower koordinater
    public static double getAngle(float x1, float y1, float x2, float y2) {
            return -utils.radiansToDegrees*(utils.atan2(y2-y1,x2-x1));
    }


    public static float angleDeg (float rotToX, float rotToY, float orgX, float orgY) {
        float angle = (float)Math.atan2(rotToY - orgY, rotToX - orgX) * MathUtils.radiansToDegrees;
        if (angle < 0) angle += 360;
        return angle;
    }


    public static double disBetweenPoints(float x1, float y1, float x2, float y2) {
        float disX = (x2-x1);
        float disY = (y2-y1);
        double length = disX * disX + disY * disY;
        return length;

    }
}
