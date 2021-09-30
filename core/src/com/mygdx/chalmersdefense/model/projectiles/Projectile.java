package com.mygdx.chalmersdefense.model.projectiles;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Elin Forsberg
 * Class defining a projectile object
 */
public abstract class Projectile implements IProjectile{

    private float width;
    private float height;
    private float speed;


    private String name;
    private float x;
    private float y;

    private int hitCountsLeft = 4;

    private float angle;



    private boolean dealtDamage;

    public Projectile(float speed, String name, float x, float y, float angle){
        this.speed = speed;
        this.name = name;
        this.x = x;
        this.y = y;
        this.angle = angle;

        // TODO Fix speed calc in children


        try{
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("projectiles/" + name + ".png")));
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
    public void move() {
        float xLength = (float) (Math.cos(Math.toRadians(angle)) * speed);
        float yLength = (float) (Math.sin(Math.toRadians(angle)) * speed);

        x =  x + xLength;
        y =  y + yLength;

    }

    public String getSpriteKey(){
        return name;
    }

    public float getAngle(){
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean getIfDealtDamage() {
        return dealtDamage;
    }

    /**
     * Method to call when virus is hit (temp for now, used by lightning)
     */
    public void virusHit(){
        if (hitCountsLeft > 0){
            hitCountsLeft--;
        } else {
            dealtDamage = true;
        }
    }

    public void setDealtDamage(boolean dealtDamage) {
        this.dealtDamage = dealtDamage;
    }

    /**
     * Gets width of projectile
     * @return width of projectile
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gets height of projectile
     * @return height of projectile
     */
    public float getHeight() {
        return height;
    }

    /**
     * Gets name of projectile
     * @return name of projectile
     */
    public String getName() {
        return name;
    }

    /**
     * Gets X-coordinate of projectile
     * @return x-coordinate of projectile
     */
    public float getX() {
        return x;
    }

    /**
     * Gets Y-coordinate of projectile
     * @return y-coordinate of projectile
     */
    public float getY() {
        return y;
    }

}
