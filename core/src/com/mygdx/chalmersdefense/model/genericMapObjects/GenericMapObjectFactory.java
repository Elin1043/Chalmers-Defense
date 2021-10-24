package com.mygdx.chalmersdefense.model.genericMapObjects;


/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Factory class for GenericMapObject
 */
public abstract class GenericMapObjectFactory {

    /**
     * Creates an object representing some bubbles on screen
     *
     * @param startPosX The starting x coordinate
     * @param startPosY The starting y coordinate
     * @param angle     The angle to move in
     * @return The newly created object representing bubbles
     */
    public static IGenericMapObject createBubbles(float startPosX, float startPosY, float angle) {
        return new GenericMapObject(2, "bubbles", startPosX, startPosY, angle, 500);
    }

    /**
     * Creates an object representing a MaskedUpSmurf
     *
     * @param startPosX The starting x coordinate
     * @param startPosY The starting y coordinate
     * @param angle     The angle to move in
     * @return The newly created object representing a MaskedUpSmurf
     */
    public static IGenericMapObject createMaskedUpSmurf(float startPosX, float startPosY, float angle) {
        return new GenericMapObject(5, "maskedUpSmurf", startPosX, startPosY, angle, 500);
    }

    /**
     * Creates an object representing a happy face mask
     *
     * @param startPosX The starting x coordinate
     * @param startPosY The starting y coordinate
     * @param angle     The angle to move in
     * @return The newly created object representing a happy face mask
     */
    public static IGenericMapObject createHappyMask(float startPosX, float startPosY, float angle) {
        return new GenericMapObject(5, "happyMask", startPosX, startPosY, angle, 500);
    }

    /**
     * Creates an object representing a big storm of flying syringes moving upwards on the screen
     *
     * @return The object representing the storm
     */
    public static IGenericMapObject createVaccinationStorm() {
        return new GenericMapObject(7, "vaccinationStorm", 0, -1500, 90, 500);
    }
}
