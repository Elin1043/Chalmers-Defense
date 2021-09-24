package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.Virus;

public abstract class Projectile {

    private int speed;



    private String imagePath;


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

    }

    public void move() {
        float xLength = (float) ((Math.cos(Math.toRadians(angle)) * speed));
        float yLength = (float) ((Math.sin(Math.toRadians(angle)) * speed));
        x =  x + xLength;
        y =  y + yLength;

    }

    public abstract void attack(Virus virus);

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
