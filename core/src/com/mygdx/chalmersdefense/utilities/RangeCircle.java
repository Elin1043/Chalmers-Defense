package com.mygdx.chalmersdefense.utilities;

/**
 * @author Elin Forsberg
 * Class used to know where and how to draw the gray circle around towers
 */
public final class RangeCircle {
    private float x;    // The x coordinate
    private float y;    // The y coordinate
    private float range;    // The range of the circle

    private Color enumColor = Color.NONE;       // The color of the circle

    /**
     * Creates an instance of a RangeCircle using coordinates
     *
     * @param x     the start x coordinate
     * @param y     the start y coordinate
     * @param range the range of the circle
     */
    public RangeCircle(float x, float y, float range) {
        this.x = x;
        this.y = y;
        this.range = range;
    }

    /**
     * Creates a RangeCircle from another given RangeCircle
     *
     * @param circle the circle to copy
     */
    public RangeCircle(RangeCircle circle) {
        this.x = circle.getX();
        this.y = circle.getY();
        this.range = circle.getRange();
        this.enumColor = circle.getColor();
    }

    /**
     * Enum for the different colors
     */
    public enum Color {
        RED,
        GRAY,
        NONE
    }

    /**
     * Update the position of the circle
     *
     * @param x     - position to be set
     * @param y     - position to be set
     * @param range to be set
     */
    public void updatePos(float x, float y, float range) {
        this.x = x;
        this.y = y;
        this.range = range;
    }

    /**
     * Sets the colour of the enum
     *
     * @param enumColor color to be set
     */
    public void setEnumColor(Color enumColor) {
        this.enumColor = enumColor;
    }

    /**
     * Get the color of the enum
     *
     * @return the color
     */
    public Color getColor() {
        return enumColor;
    }

    /**
     * Get the x - position of the cirlce
     *
     * @return x - position
     */
    public float getX() {
        return x;
    }

    /**
     * Get the y - position of the cirlce
     *
     * @return y - position
     */
    public float getY() {
        return y;
    }

    /**
     * Get the range of the cirlce
     *
     * @return range
     */
    public float getRange() {
        return range;
    }


}
