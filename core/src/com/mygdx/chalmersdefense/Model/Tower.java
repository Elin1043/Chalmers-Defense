package com.mygdx.chalmersdefense.Model;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * @author ELin Forsberg
 * A class defining the tower objects
 *
 */

public class Tower extends Actor {
    private final Sprite sprite;
    private final String spritePath;
    private int angle = 0;
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




    public Tower(float x, float y, Sprite sprite, String spritePath, String name, int attackDamage, int attackSpeed, int cost){
        this.sprite = sprite;
        this.spritePath = spritePath;
        this.name=name;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;

        this.setPos(x,y);
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.range = 100;
        this.cost = cost;
        this.collision = false;
        this.gotButton = false;

    }
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

    public Sprite getSprite(){
        return sprite;
    }


    public int getAngle(){
        return angle;
    }

    public void setAngle(int setangle){
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


}
