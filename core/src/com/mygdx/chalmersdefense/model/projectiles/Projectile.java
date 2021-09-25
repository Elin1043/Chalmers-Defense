package com.mygdx.chalmersdefense.model.projectiles;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Elin Forsberg
 * Class defining a projectile object
 */
public abstract class Projectile {

    private int width;
    private int height;
    private int speed;


    private String name;
    private float x;
    private float y;
    private double angle;

    public Projectile(int speed,String name, float x, float y, double angle){
        this.speed = speed;
        this.name = name;
        this.x = x;
        this.y = y;
        this.angle = angle;

        try{
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("projectiles/" +name + ".png")));
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
        float xLength = (float) ((Math.cos(Math.toRadians(angle)) * speed));
        float yLength = (float) ((Math.sin(Math.toRadians(angle)) * speed));

        x =  x + xLength;
        y =  y + yLength;

    }

    public abstract Projectile createProjectile(int speed, float x, float y, double angle);

    /**
     * Gets width of projectile
     * @return width of projectile
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height of projectile
     * @return height of projectile
     */
    public int getHeight() {
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
