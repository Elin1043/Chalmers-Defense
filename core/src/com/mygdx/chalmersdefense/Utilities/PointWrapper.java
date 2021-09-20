package com.mygdx.chalmersdefense.Utilities;

import java.awt.*;
import java.awt.geom.Point2D;

public class PointWrapper {

    private Point2D.Float point;

    public PointWrapper(float x, float y) {
        point= new Point2D.Float(x, y);

    }

    public PointWrapper(PointWrapper pointWrapper) {
        point = new Point2D.Float(pointWrapper.getX(), pointWrapper.getY());
    }

    public float getX() {
        return point.x;
    }

    public float getY() {
        return point.y;
    }

    public void setX(float x) {
        point.x = x;
    }

    public void setY(float y) {
        point.y = y;
    }


}
