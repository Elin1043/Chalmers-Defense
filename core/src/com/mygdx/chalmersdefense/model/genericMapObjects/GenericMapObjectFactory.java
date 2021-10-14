package com.mygdx.chalmersdefense.model.genericMapObjects;


/**
 * @author Joel Båtsman Hilmersson
 *
 * Factory class for GenericMapObject
 */
public abstract class GenericMapObjectFactory {


    public static IGenericMapObject createBubbles(float startPosX, float startPosY, float angle) {
        return new GenericMapObject(2,"bubbles", startPosX, startPosY, angle, 500);
    }

    public static IGenericMapObject createMaskedUpSmurf(float startPosX, float startPosY, float angle) {
        return new GenericMapObject(5,"maskedUpSmurf", startPosX, startPosY, angle, 500);
    }

    public static IGenericMapObject createVaccinationStorm() {
        return new GenericMapObject(7,"vaccinationStorm", 0,-1500,90, 500);
    }
}
