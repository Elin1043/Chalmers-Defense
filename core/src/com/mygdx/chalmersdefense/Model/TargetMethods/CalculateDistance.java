package com.mygdx.chalmersdefense.Model.TargetMethods;

public class CalculateDistance {

    public static double distanceFromVirus(int posX, int posY, int virusX, int virusY) {
        int distanceVectorX = virusX - posX;
        int distanceVectorY = virusY - virusX;
        double length = distanceVectorX * distanceVectorX + distanceVectorY * distanceVectorY; //C^2 = (a^2+b^2)
        return length;

    }
}
