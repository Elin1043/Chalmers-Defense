package com.mygdx.chalmersdefense.model;

/**
 * @author Joel Båtsman Hilmersson
 * Interface for map objects
 */

public interface IMapObject {

    /**
     * Gets the X-position of the object
     *
     * @return x-coordinate of object
     */
    float getX();

    /**
     * Gets the Y-position of the object
     *
     * @return y-coordinate of object
     */
    float getY();

    /**
     * Gets the height of object
     *
     * @return height of object
     */
    float getHeight();

    /**
     * Gets the width of object
     *
     * @return width of object
     */
    float getWidth();

    /**
     * Get the spriteKey of object
     *
     * @return the spriteKey
     */
    String getSpriteKey();

    /**
     * Gets the angle of the object
     *
     * @return angle of object
     */
    float getAngle();


}
