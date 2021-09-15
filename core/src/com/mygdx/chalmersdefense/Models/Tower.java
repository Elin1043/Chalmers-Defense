package com.mygdx.chalmersdefense.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.chalmersdefense.Vectors;

public class Tower{
    private final Vectors pos;
    private final Texture texture;
    private final int angle;
    private double range;
    private String name;
    private Tower upgrade;
    private boolean isPlaced=false;

    private int attackDamage;
    private int attackSpeed;



    public Tower(Vectors pos, Texture texture, int angle, String name, int attackDamage, int attackSpeed) {
        this.pos = pos;
        this.texture = texture;
        this.angle = angle;
        this.name=name;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }

    public String getName(){
        return name;
    }

    public Texture getTexture(){
        return texture;
    }

    public Vectors getPos(){
        return pos;
    }

    public int getAngle(){
        return angle;
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


}
