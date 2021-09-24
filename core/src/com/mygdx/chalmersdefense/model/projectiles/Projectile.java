package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.Virus;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
        this.angle = angle + 61;

        try{
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("projectiles/" +name + ".png")));
            this.width         = towerImage.getWidth();
            this.height        = towerImage.getHeight();


        }
        catch (IOException exception){
            System.out.println(exception);
        }

    }

    public void move() {
        float xLength = (float) ((Math.cos(Math.toRadians(angle)) * speed));
        float yLength = (float) ((Math.sin(Math.toRadians(angle)) * speed));
        x =  x + xLength;
        y =  y + yLength;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
