package com.mygdx.chalmersdefense.model.towers;



import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.chalmersdefense.model.projectiles.BulletProjectile;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
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

    public Projectile shootProjectile(){
        if(currentReload < 1 && gotTarget && isPlaced){
            Projectile projectile = new BulletProjectile(attackSpeed, this.getPosX() + width/2, this.getPosY() + height/2, this.angle);
            currentReload = reloadTime;
            return projectile;
        }
        else{
            currentReload --;
        }
        return null;
    }


    public void update() { shootProjectile(); }

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
        if (isPlaced) { angle = setangle; }
    }

    public float getRange(){
        return range;
    }

    public ITargetMode getCurrentTargetMode() { return currentTargetMode; }

    public boolean isPlaced(){
        return isPlaced;
    }
    public void placeTower(){
        isPlaced = true;
    }

    public void haveTarget() { gotTarget = true; }
    public void notHaveTarget() { gotTarget = false; }


}
