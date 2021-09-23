package com.mygdx.chalmersdefense.Model.TargetMode;

import com.badlogic.gdx.math.MathUtils;

public abstract class Calculate {

    private static MathUtils utils;

    public static double getAngle(float x1, float y1, float x2, float y2) {
            return utils.radiansToDegrees*(utils.atan2(y2-y1,x2-x1));
        }


    public static double disBetweenPoints(float x1, float y1, float x2, float y2) {
        float disX = (x2-x1);
        float disY = (y2-y1);
        double length = disX * disX + disY * disY;
        return length;

    }
}
