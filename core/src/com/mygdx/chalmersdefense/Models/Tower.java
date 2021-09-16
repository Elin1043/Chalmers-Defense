package com.mygdx.chalmersdefense.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.chalmersdefense.Vectors;

public class Tower{
    private final Vectors pos;
    private final Sprite sprite;
    private int angle = 0;
    private double range;
    private String name;
    private Tower upgrade;
    private boolean isPlaced=false;

    private int attackDamage;
    private int attackSpeed;



    public Tower(Vectors pos, Sprite sprite, String name, int attackDamage, int attackSpeed) {
        this.pos = pos;
        this.sprite = sprite;
        this.name=name;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;

        sprite.setPosition(pos.x, pos.y);
    }

    public String getName(){
        return name;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public Vectors getPos(){
        return pos;
    }
    public void setPos(int x, int y){
        pos.x = x;
        pos.y = y;
    }

    public int getAngle(){
        return angle;
    }

    public void setAngle(int setangle){
        angle = setangle;

    }

    public double getRange(){
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


    public void addListener(ClickListener clickListener) {
    }
}
