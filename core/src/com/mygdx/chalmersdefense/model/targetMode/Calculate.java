package com.mygdx.chalmersdefense.model.targetMode;


public abstract class Calculate {


    public static float angleDeg (float rotToX, float rotToY, float orgX, float orgY) {
        float angle = (float) Math.toDegrees(Math.atan2(rotToY - orgY, rotToX - orgX));

        if(angle < 0){
            angle += 360;
        }

        return angle - 61;
    }


    public static double disBetweenPoints(float x1, float y1, float x2, float y2) {
        float disX = (x2-x1);
        float disY = (y2-y1);
        double length = disX * disX + disY * disY;
        return length;

    }
}
