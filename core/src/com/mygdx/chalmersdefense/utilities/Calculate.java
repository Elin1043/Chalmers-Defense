package com.mygdx.chalmersdefense.utilities;


import com.mygdx.chalmersdefense.model.Virus;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;

import java.awt.*;

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

    public static boolean objectsIntersects(Projectile o1, Rectangle o2) {
        double o1Width = o1.getWidth();
        double o1Height = o1.getHeight();
        double o2Width = o2.getWidth();
        double o2Height = o2.getHeight();


        if (o2Width <= 0 || o2Height <= 0 || o1Width <= 0 || o1Height <= 0) {
            return false;
        }
        double o1X = o1.getX();
        double o1Y = o1.getY();
        double o2X = o2.getX();
        double o2Y = o2.getY();

        o1Width += o1X;
        o1Height += o1Y;
        o2Width += o2X;
        o2Height += o2Y;

        //      overflow || intersect
        return ((o2Width < o2X || o2Width > o1X) &&
                (o2Height < o2Y || o2Height > o1Y) &&
                (o1Width < o1X || o1Width > o2X) &&
                (o1Height < o1Y || o1Height > o2Y));
    }

    public static boolean objectsIntersects(Projectile o1, Virus o2) {
        double o1Width = o1.getWidth();
        double o1Height = o1.getHeight();
        double o2Width = o2.getWidthX();
        double o2Height = o2.getHeightY();


        if (o2Width <= 0 || o2Height <= 0 || o1Width <= 0 || o1Height <= 0) {
            return false;
        }
        double o1X = o1.getX();
        double o1Y = o1.getY();
        double o2X = o2.getX();
        double o2Y = o2.getY();

        o1Width += o1X;
        o1Height += o1Y;
        o2Width += o2X;
        o2Height += o2Y;

        //      overflow || intersect
        return ((o2Width < o2X || o2Width > o1X) &&
                (o2Height < o2Y || o2Height > o1Y) &&
                (o1Width < o1X || o1Width > o2X) &&
                (o1Height < o1Y || o1Height > o2Y));
    }
}
