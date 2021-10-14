package com.mygdx.chalmersdefense.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Elin Forsberg
 * Class defining a generic MapObject
 *
 */
public class GenericMapObject implements IMapObject {


    private float width;    // Current width of object
    private float height;   // Current height of object
    private final float speed;    // Speed of object



    private String spriteKey;   // The key to the Sprite Hashmap
    private float x;            // X coordinate on map
    private float y;            // y coordinate on map

    private float angle;        // Current angle of object

    boolean canRemove = false;  // Boolean over if this object can be removed by map

    GenericMapObject(float speed, String spriteKey, float x, float y, float angle) {
        this.speed = speed;
        this.spriteKey = spriteKey;
        this.x = x;
        this.y = y;
        this.angle = angle;


        try {
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("genericMapObjects/" + spriteKey + ".png")));
            this.width = towerImage.getWidth();
            this.height = towerImage.getHeight();


        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }



    public void update(float angle) {
        float xLength = (float) (Math.cos(Math.toRadians(this.angle)) * speed);
        float yLength = (float) (Math.sin(Math.toRadians(this.angle)) * speed);

        x = x + xLength;
        y = y + yLength;

    }



    @Override
    public String getSpriteKey() {
        return spriteKey;
    }

    @Override
    public float getAngle() {
        return angle;
    }


    /**
     * Set the angle of the object
     *
     * @param angle to be set
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }


    public boolean canRemove() {
        return canRemove;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }


}
