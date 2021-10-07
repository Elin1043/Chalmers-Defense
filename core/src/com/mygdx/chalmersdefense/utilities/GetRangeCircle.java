package com.mygdx.chalmersdefense.utilities;

/**
 * @author Elin Forsberg
 * Class used to know where and how to draw the gray circle around towers
 */
public class GetRangeCircle {
    private float x;
    private float y;
    private float range;

    private Color enumColor = Color.NONE;

    public enum Color {
        RED,
        GRAY,
        NONE
    }

    public void updatePos(float x, float y, float range) {
        this.x = x;
        this.y = y;
        this.range = range;
    }

    public void setEnumColor(Color enumColor) {
        this.enumColor = enumColor;
    }

    public Color getColor() {
        return enumColor;
    }

    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }


    public float getRange() {
        return range;
    }


}
