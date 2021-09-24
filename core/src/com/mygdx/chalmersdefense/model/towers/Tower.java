package com.mygdx.chalmersdefense.model.towers;



import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.chalmersdefense.model.Virus;
import com.mygdx.chalmersdefense.model.projectiles.BulletProjectile;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
import com.mygdx.chalmersdefense.utilities.Calculate;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
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

public class Tower extends Actor {

    private String spriteKey;
    private int upgradeLevel = 1;

    private double angle = 0;
    private float range;
    private String name;
    //private Tower upgrade;
    private boolean isPlaced = false;
    private float x;
    private float y;


    //private final TargetMode firstMode = TargetModeFactory.

    private final List<ITargetMode> targetModes;
    private ITargetMode currentTargetMode;

    private float width;
    private float height;

    private int attackSpeed;
    private int cost;

    private boolean collision;
    private boolean gotButton;

    private boolean gotTarget;

    private int reloadTime = 60; //how many updates from model
    private int currentReload = 0;
    private Virus currentTarget;


    private Rectangle rectangle = new Rectangle();




    public Tower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes){
        this.name=name;
        this.attackSpeed = attackSpeed;
        this.targetModes = targetModes;
        this.currentTargetMode = targetModes.get(0);
        updateSpriteKey();

        try{
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("towers/" +spriteKey + ".png")));
            this.width         = towerImage.getWidth();
            this.height        = towerImage.getHeight();


        }
        catch (IOException exception){
            exception.printStackTrace();
        }

        this.setPos(x,y);
        this.range = range;
        this.cost = cost;
        this.gotTarget = false;
        this.collision = false;
        this.gotButton = false;


    }

//    public void target(List<Virus> viruses) {
//        if (viruses != null && this.isPlaced()) {
//            currentTarget = currentTargetMode.getTarget(viruses, this.getPosX(), this.getPosY(), range);
//            if (currentTarget != null) {
//                this.setAngle( Calculate.angleDeg(currentTarget.getX(), currentTarget.getY(), this.getPosX(), this.getPosY()));
//            }
//        }
//    }

    public Projectile createProjectile() {
        if (currentTarget != null){
            return new BulletProjectile(attackSpeed,this.getPosX(), this.getPosY(), this.angle);
        }
        return null;
    }


    public Projectile shoot(){
        if(currentReload < 1 && currentTarget != null){
            Projectile projectile = createProjectile();
            currentReload = reloadTime;
            return projectile;
        }
        else{
            currentReload --;
        }
        return null;
    }


    public void update(List<Virus> viruses) {
        //target(viruses);
        shoot();
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
        rectangle.setRect(this.x  , this.y  , this.width,this.height);
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

    public double getAngle(){
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
    public void placeTower(){
        isPlaced = true;
    }



}
