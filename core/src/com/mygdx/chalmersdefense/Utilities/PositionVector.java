package com.mygdx.chalmersdefense.Utilities;

public class PositionVector {

    private final float x;
    private final float y;

    public PositionVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PositionVector(PositionVector positionVector) {
        this.x = positionVector.getX();
        this.y = positionVector.getY();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public PositionVector setVectorPos(float x, float y) {
        return new PositionVector(x, y);
    }

    public PositionVector setX(float x) {
        return new PositionVector(x, this.y);
    }

    public PositionVector setY(float y) {
        return new PositionVector(this.x, y);
    }

}
