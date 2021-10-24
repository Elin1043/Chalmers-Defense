package com.mygdx.chalmersdefense.model.modelUtilities;


/**
 * @author Daniel Persson
 * @author Jenny Carlsson
 *
 * Class representing a positionVector
 */
public final class PositionVector {

    private final float x;  // The x coordinate
    private final float y;  // The y coordinate

    /**
     * Creates a vector with given coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public PositionVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a vector from a previous vector
     * @param positionVector the given vector to copy
     */
    public PositionVector(PositionVector positionVector) {
        this.x = positionVector.getX();
        this.y = positionVector.getY();
    }

    /**
     * Get the x - position of the positionVector
     * @return x - position
     */
    public float getX() {
        return x;
    }

    /**
     * Get the y - position of the positionVector
     * @return y - position
     */
    public float getY() {
        return y;
    }

    /**
     * Set the position of a new positionVector
     * @return new positionVector
     */
    public PositionVector setVectorPos(float x, float y) {
        return new PositionVector(x, y);
    }

    /**
     * Set the x - position of the positionVector
     * @return x - position
     */
    public PositionVector setX(float x) {
        return new PositionVector(x, this.y);
    }

    /**
     * Set the y - position of the positionVector
     * @return y - position
     */
    public PositionVector setY(float y) {
        return new PositionVector(this.x, y);
    }

}
