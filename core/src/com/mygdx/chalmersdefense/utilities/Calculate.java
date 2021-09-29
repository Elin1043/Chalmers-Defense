package com.mygdx.chalmersdefense.utilities;


import com.mygdx.chalmersdefense.model.viruses.Virus;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elin Forsberg
 * Util class to help with calculations
 *
 * 2021-09-24 Modified by Joel Båtsman Hilmersson: Placed getVirusesInRange method here instead of tower class
 *
 */
public abstract class Calculate {
    /**
     * Gets the viruses in range of a tower
     * @param towerX x-coorindate of tower
     * @param towerY y-coorindate of tower
     * @param towerRange range of tower
     * @param allViruses list of all viruses
     * @return a list of viruses in range
     */
    public static List<Virus> getVirusesInRange(float towerX, float towerY, float towerRange, List<Virus> allViruses) {
        List<Virus> virusList = new ArrayList<>();

        for (Virus virus : allViruses) {
            if (disBetweenPoints(towerX, towerY, virus.getX(), virus.getY()) < towerRange) {
                virusList.add(virus);
            }
        }
        return virusList;
    }


    /**
     * Calculate the angle between two objects
     * @param rotToX x-coordinate of object to rotate towards
     * @param rotToY y-coordinate of object to rotate towards
     * @param orgX x-coordinate of object to rotate
     * @param orgY y-coordinate of object to rotate
     * @return angle to rotate by
     */
    public static float angleDeg (float rotToX, float rotToY, float orgX, float orgY) {
        float angle = (float) Math.toDegrees(Math.atan2(rotToY - orgY, rotToX - orgX));
        if(angle < 0){
            angle += 360;
        }
        return angle;
    }

    /**
     * Calculate distance between two points
     * @param x1 x-coordinate of first point
     * @param y1 y-coordinate of first point
     * @param x2 x-coordinate of second point
     * @param y2 y-coordinate of second point
     * @return distance between points
     */
    public static double disBetweenPoints(float x1, float y1, float x2, float y2) {
        float disX = (x2-x1);
        float disY = (y2-y1);
        return Math.sqrt(disX * disX + disY * disY);

    }

    /**
     * Checks if a projectile and rectangle intersect
     * @param o1 the projectile
     * @param o2 the rectangle
     * @return if intersects
     */
    public static boolean objectsIntersects(Projectile o1, Rectangle o2) {
        double o1Width = o1.getWidth();
        double o1Height = o1.getHeight();
        double o2Width = o2.getWidth();
        double o2Height = o2.getHeight();

        double o1X = o1.getX();
        double o1Y = o1.getY();
        double o2X = o2.getX();
        double o2Y = o2.getY();


        return calculateIntersects(o1Width,o1Height,o2Width,o2Height,o1X,o1Y,o2X,o2Y);
    }

    /**
     * Checks if a projectile and virus intersect
     * @param o1 the projectile
     * @param o2 the virus
     * @return if intersects
     */
    public static boolean objectsIntersects(Projectile o1, Virus o2) {
        double o1Width = o1.getWidth();
        double o1Height = o1.getHeight();
        double o2Width = o2.getWidth();
        double o2Height = o2.getHeight();

        double o1X = o1.getX();
        double o1Y = o1.getY();
        double o2X = o2.getX();
        double o2Y = o2.getY();

        return calculateIntersects(o1Width,o1Height,o2Width,o2Height,o1X,o1Y,o2X,o2Y);

    }

    //Calculates if two objects intersect
    private static boolean calculateIntersects(double o1Width, double o1Height, double o2Width, double o2Height, double o1X, double o1Y, double o2X, double o2Y){
        if (o2Width <= 0 || o2Height <= 0 || o1Width <= 0 || o1Height <= 0) {
            return false;
        }
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
