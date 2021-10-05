package com.mygdx.chalmersdefense.model.projectiles;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Elin Forsberg
 * Class defining a projectile object
 *
 * 2021-10-01 Modified by Joel Båtsman Hilmersson: The projectile now holds a list of hashcodes to every virus it has hit before
 */
abstract class Projectile implements IProjectile{

    final List<Integer> haveHitList = new ArrayList<>();

    private float width;
    private float height;
    private float speed;


    private String spriteKey;
    private float x;
    private float y;

    private float angle;

    boolean canRemove = false;

    Projectile(float speed, String spriteKey, float x, float y, float angle){
        this.speed = speed;
        this.spriteKey = spriteKey;
        this.x = x;
        this.y = y;
        this.angle = angle;
        // TODO Fix speed calc in children


        try{
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("projectiles/" + spriteKey + ".png")));
            this.width         = towerImage.getWidth();
            this.height        = towerImage.getHeight();


        }
        catch (IOException exception){
            exception.printStackTrace();
        }

    }

    /**
     * Moves the projectile in calculated direction
     */
    @Override
    public void update(boolean hitVirus, int haveHit, float angle) {
        if (hitVirus) { virusIsHit(haveHit, angle); }


        float xLength = (float) (Math.cos(Math.toRadians(this.angle)) * speed);
        float yLength = (float) (Math.sin(Math.toRadians(this.angle)) * speed);

        x =  x + xLength;
        y =  y + yLength;

    }

    /**
     * Method to call when virus is hit
     */
    void virusIsHit(int haveHit, float angle){
        haveHitList.add(haveHit);
        this.canRemove = true;
    }

    @Override
    public String getSpriteKey(){
        return spriteKey;
    }

    @Override
    public float getAngle(){
        return angle;
    }

    void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public boolean canRemove(){
        return canRemove;
    }

    @Override
    public boolean haveHitBefore(int hashCode){
        return haveHitList.contains(hashCode);
    }

    /**
     * Gets width of projectile
     * @return width of projectile
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * Gets height of projectile
     * @return height of projectile
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * Gets X-coordinate of projectile
     * @return x-coordinate of projectile
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * Gets Y-coordinate of projectile
     * @return y-coordinate of projectile
     */
    @Override
    public float getY() {
        return y;
    }

}
