package com.mygdx.chalmersdefense.Model;



import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * @author ELin Forsberg
 * A class defining the tower objects
 *
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: changed class to hold hashmap key for sprite
 */

public class Tower extends Actor {

    private String spriteKey;
    private int upgradeLevel = 1;

    private int angle = 0;
    private float range;
    private String name;
    //private Tower upgrade;
    private boolean isPlaced = false;
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




    public Tower(float x, float y, String name, int attackDamage, int attackSpeed, int cost){
        this.name=name;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;


        this.width = 100;
        this.height = 100;
        this.setPos(x,y);
        this.range = 100;
        this.cost = cost;
        this.collision = false;
        this.gotButton = false;

        updateSpriteKey();
    }

    private void updateSpriteKey() { spriteKey = name + upgradeLevel; }

    public String getSpriteKey() {
        return spriteKey;
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

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
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
