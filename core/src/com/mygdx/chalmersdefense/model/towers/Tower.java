package com.mygdx.chalmersdefense.model.towers;



import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.chalmersdefense.model.Virus;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.TargetMode;
import com.mygdx.chalmersdefense.model.targetMode.TargetModeFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


/**
 * @author ELin Forsberg
 * A class defining the tower objects
 *
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: changed class to hold hashmap key for sprite
 */

public abstract class Tower extends Actor {

    private String spriteKey;
    private int upgradeLevel = 1;

    private float angle = 0;
    private float range;
    private String name;
    //private Tower upgrade;
    private boolean isPlaced = false;
    private float x;
    private float y;


    //private final TargetMode firstMode = TargetModeFactory.

    private TargetMode currentTargetMode;

    private float width;
    private float height;

    private int attackDamage;
    private int attackSpeed;
    private int cost;

    private boolean collision;
    private boolean gotButton;


    private java.awt.Rectangle rectangle = new java.awt.Rectangle();




    public Tower(float x, float y, String name, int attackDamage, int attackSpeed, int cost, int range, TargetMode mode){
        this.name=name;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.currentTargetMode = mode;
        updateSpriteKey();

        try{
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("towers/" +spriteKey + ".png")));
            this.width         = towerImage.getWidth();
            this.height        = towerImage.getHeight();


        }
        catch (IOException exception){
            System.out.println(exception);
        }

        this.setPos(x,y);
        this.range = range;
        this.cost = cost;
        this.collision = false;
        this.gotButton = false;


    }

    private void updateSpriteKey() { spriteKey = name + upgradeLevel; }

    public String getSpriteKey() {
        return spriteKey;
    }


    public abstract Projectile shoot();


    public int getCost() {
        return cost;
    }

    public boolean getGotButton() {
        return gotButton;
    }

    public void setGotButton(boolean gotButton) {
        this.gotButton = gotButton;
    }
    public boolean getCollision(){
        return collision;
    }

    public void setCollision(Boolean set){
        collision = set;
    }

    public void setRectangle(){
        rectangle = new Rectangle();
        rectangle.setRect(this.x  , this.y  , this.width,this.height);
    }

    public java.awt.Rectangle getRectangle(){
        return rectangle;
    }

    public String getName(){
        return name;
    }

    public void setPos(float x, float y){
        this.x = x;
        this.y = y;
    }
    public float getPosX(){
        return x;
    }

    public float getHeight(){
        return height;
    }

    public float getWidth(){
        return width;
    }


    public float getPosY(){
        return y;
    }

    public float getAngle(){
        return angle;
    }

    public void setAngle(float setangle){
        angle = setangle;

    }

    public float getRange(){
        return range;
    }

    public boolean isPlaced(){
        return isPlaced;
    }
    public void setPlaced(boolean placed){
        isPlaced = placed;
    }

    public int getAttackDamage() {
        return attackDamage;
    }


    public abstract void update(List<Virus> viruses);


}
