package com.mygdx.chalmersdefense.model.genericMapObjects;

import com.mygdx.chalmersdefense.utilities.CountDownTimer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Elin Forsberg
 * Class defining a generic MapObject
 *
 */
final class GenericMapObject implements IGenericMapObject {

    private float width;    // Current width of object
    private float height;   // Current height of object
    private final float speed;    // Speed of object

    private final String spriteKey;   // The key to the Sprite Hashmap
    private float x;            // X coordinate on map
    private float y;            // y coordinate on map

    private final float angle;        // Current angle of object

    boolean canRemove = false;  // Boolean over if this object can be removed by map

    private final CountDownTimer animationTimer ;

    GenericMapObject(float speed, String spriteKey, float x, float y, float angle, int time) {
        this.speed = speed;
        this.spriteKey = spriteKey;
        this.x = x;
        this.y = y;
        this.angle = angle;

        animationTimer = new CountDownTimer(time);


        try {
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("genericMapObjects/" + spriteKey + ".png")));
            this.width = towerImage.getWidth();
            this.height = towerImage.getHeight();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }



    @Override
    public void update() {
        float xLength = (float) (Math.cos(Math.toRadians(this.angle)) * speed);
        float yLength = (float) (Math.sin(Math.toRadians(this.angle)) * speed);

        x = x + xLength;
        y = y + yLength;

        if(animationTimer.haveReachedZero()){
            canRemove = true;
        }

    }


    @Override
    public String getSpriteKey() {
        return spriteKey;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
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
