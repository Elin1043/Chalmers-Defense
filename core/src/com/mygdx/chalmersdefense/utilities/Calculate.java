package com.mygdx.chalmersdefense.utilities;


import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elin Forsberg
 * Util class to help with calculations
 * <p>
 * 2021-09-24 Modified by Joel Båtsman Hilmersson: Placed getVirusesInRange method here instead of tower class
 */
public abstract class Calculate {
    /**
     * Gets the viruses in range of a tower
     *
     * @param towerX     x-coordinate of tower
     * @param towerY     y-coordinate of tower
     * @param towerRange range of tower
     * @param allViruses list of all viruses
     * @return a list of viruses in range
     */
    public static List<IVirus> getVirusesInRange(float towerX, float towerY, float towerRange, List<IVirus> allViruses) {
        List<IVirus> virusList = new ArrayList<>();

        for (IVirus virus : allViruses) {
            if (disBetweenPoints(towerX, towerY, virus.getX(), virus.getY()) < towerRange) {
                virusList.add(virus);
            }
        }
        return virusList;
    }


    /**
     * Calculate the angle between two objects
     *
     * @param rotToX x-coordinate of object to rotate towards
     * @param rotToY y-coordinate of object to rotate towards
     * @param orgX   x-coordinate of object to rotate
     * @param orgY   y-coordinate of object to rotate
     * @return angle to rotate by
     */
    public static float angleDeg(float rotToX, float rotToY, float orgX, float orgY) {
        float angle = (float) Math.toDegrees(Math.atan2(rotToY - orgY, rotToX - orgX));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * Calculate distance between two points
     *
     * @param x1 x-coordinate of first point
     * @param y1 y-coordinate of first point
     * @param x2 x-coordinate of second point
     * @param y2 y-coordinate of second point
     * @return distance between points
     */
    public static double disBetweenPoints(float x1, float y1, float x2, float y2) {
        float disX = (x2 - x1);
        float disY = (y2 - y1);
        return Math.sqrt(disX * disX + disY * disY);

    }


    /**
     * Checks if two IMapObjects intersects
     *
     * @param objOne the first object
     * @param objTwo the second object
     * @return True - if the objects intersect, False - if the do not
     */
    public static boolean objectsIntersects(IMapObject objOne, IMapObject objTwo) {
        return calculateIntersects(objOne.getWidth(), objOne.getHeight(), objTwo.getWidth(), objTwo.getHeight(), objOne.getX(), objOne.getY(), objTwo.getX(), objTwo.getY());
    }

    /**
     * Check if two objects values intersects
     * @param obj1Width width of object 1
     * @param obj1Height height of object 1
     * @param obj2Width width of object 2
     * @param obj2Height height of object 2
     * @param obj1X x-coordinate of object 1
     * @param obj1Y y-coordinate of object 1
     * @param obj2X x-coordinate of object 2
     * @param obj2Y y-coordinate of object 2
     * @return if they collide
     */
    public static boolean calculateIntersects(double obj1Width, double obj1Height, double obj2Width, double obj2Height, double obj1X, double obj1Y, double obj2X, double obj2Y) {
        if (obj2Width <= 0 || obj2Height <= 0 || obj1Width <= 0 || obj1Height <= 0) {
            return false;
        }
        obj1Width += obj1X;
        obj1Height += obj1Y;
        obj2Width += obj2X;
        obj2Height += obj2Y;

        //      overflow || intersect
        return ((obj2Width < obj2X || obj2Width > obj1X) &&
                (obj2Height < obj2Y || obj2Height > obj1Y) &&

                (obj1Width < obj1X || obj1Width > obj2X) &&
                (obj1Height < obj1Y || obj1Height > obj2Y));
    }
}
