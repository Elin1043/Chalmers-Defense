package com.mygdx.chalmersdefense.utilities;

import com.mygdx.chalmersdefense.model.IMapObject;


import java.awt.Rectangle;


/**
 * @author Joel Båtsman Hilmersson
 *
 * Class for wraping a java rectangle
 */
public class PathRectangle implements IMapObject {
    private final Rectangle rectangle = new Rectangle();

    public PathRectangle(float x, float y, float width, float height){
        rectangle.setRect(x, y, width, height);
    }



    @Override
    public float getX() {
        return (float) rectangle.getX();
    }

    @Override
    public float getY() {
        return (float) rectangle.getY();
    }

    @Override
    public float getHeight() {
        return (float) rectangle.getHeight();
    }

    @Override
    public float getWidth() {
        return (float) rectangle.getWidth();
    }

    @Override
    public String getSpriteKey() {
        // Trow?
        return "If this is called, it should crash";
    }

    @Override
    public float getAngle() {
        // Trow?
        return 0;
    }
}
