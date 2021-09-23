package com.mygdx.chalmersdefense.Model.Towers;



import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.chalmersdefense.Model.Virus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ELin Forsberg
 * A class defining the tower objects
 *
 */

public abstract class Tower extends Actor {

    private final String spritePath;
    private float angle = 0;
    private float range;
    private String name;
    //private Tower upgrade;
    private boolean isPlaced=false;
    private float x;
    private float y;


    private float width;
    private float height;

    private int attackDamage;
    private int attackSpeed;
    private int cost;

    private boolean collision;
    private boolean gotButton;



    private Rectangle rectangle = new Rectangle();




    public Tower(float x, float y, String spritePath, String name, int attackDamage, int attackSpeed, int cost, int range) {
        this.spritePath = spritePath;
        this.name=name;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;


        try{
            BufferedImage towerImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream(spritePath));
            this.width         = towerImage.getWidth();
            this.height        = towerImage.getHeight();
        }
        catch (Exception exception){
            System.out.println(exception);
        }


        this.setPos(x,y);
        this.range = range;
        this.cost = cost;
        this.collision = false;
        this.gotButton = false;

    }
    public int getCost() {
        return cost;
    }

    public String getSpritePath() {
        return spritePath;
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
        rectangle.set(this.x  , this.y  , this.width,this.height);
    }


    public Rectangle getRectangle(){
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
        isPlaced=placed;
    }
    public int getAttackDamage() {
        return attackDamage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }



    public abstract void update(List<Virus> viruses);


}
