package com.mygdx.chalmersdefense.Model.Projectiles;

public class Projectile {

    private int startPosX;
    private int startPosY;
    private double angle;
    private String imagePath;
    private int speed;

    public Projectile(int startPosX, int startPosY, double angle, String imagePath, int speed) {
        this.startPosX = startPosX;
        this.startPosY = startPosY;
        this.angle = angle;
        this.imagePath = imagePath;
        this.speed = speed;
    }


    public int getStartPosX() {
        return startPosX;
    }

    public int getStartPosY() {
        return startPosY;
    }

    public double getAngle() {
        return angle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getSpeed() {
        return speed;
    }
}
