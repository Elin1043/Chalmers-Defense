package com.mygdx.chalmersdefense.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.chalmersdefense.Vectors;

public class Tower extends Actor {
    //private final Vectors pos;
    private final Sprite sprite;
    private int angle = 0;
    private float range;
    private String name;
    private Tower upgrade;
    private boolean isPlaced=false;
    private float x;
    private float y;
    private float width;
    private float height;

    private int attackDamage;
    private int attackSpeed;



    public Tower(float x, float y, Sprite sprite, String name, int attackDamage, int attackSpeed){
        this.sprite = sprite;
        this.name=name;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;

        sprite.setPosition(x, y);
        this.setPos(x,y);
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.range = 100;
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
