package com.mygdx.chalmersdefense.model.towers;



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

public class Tower {

    private String spriteKey;
    private int upgradeLevel = 1;

    private double angle = 0;
    private int range;
    private String name;
    //private Tower upgrade;
    private boolean isPlaced = false;
    private float x;
    private float y;


    //private final TargetMode firstMode = TargetModeFactory.

    private final List<ITargetMode> targetModes;
    private ITargetMode currentTargetMode;
    private Projectile projectile;

    private float width;
    private float height;

    private int attackSpeed;
    private int cost;

    private boolean collision;
    private boolean gotButton;



    private boolean gotTarget;

    private int reloadTime = 60*3; //how many updates from model
    private int currentReload = 0;


    private Rectangle rectangle = new Rectangle();


    public Tower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes, Projectile projectile){
        this.name=name;
        this.attackSpeed = attackSpeed;
        this.targetModes = targetModes;
        this.currentTargetMode = targetModes.get(0);
        this.projectile = projectile;
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

    /**
     * Creates a projectile to shoot
     * @return projectile created
     */
    public Projectile shootProjectile(){
        if(currentReload < 1 && gotTarget && isPlaced){
            projectile = projectile.createProjectile(attackSpeed, this.getPosX() + this.width/2, this.getPosY() + this.height/2, this.angle);
            currentReload = reloadTime;
            return projectile;
        }
        else{
            currentReload --;
        }
        return null;
    }


    private void updateSpriteKey() { spriteKey = name + upgradeLevel; }

    /**
     * Get the spriteKey of tower
     * @return the spriteKey
     */
    public String getSpriteKey() {
        return spriteKey;
    }

    /**
     * Gets the cost of tower
     * @return cost of tower
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets if tower has a button
     * @return if tower got button
     */
    public boolean getGotButton() {
        return gotButton;
    }

    /**
     * Sets if tower has a button
     * @param gotButton if tower got button
     */
    public void setGotButton(boolean gotButton) {
        this.gotButton = gotButton;
    }

    /**
     * Gets if tower is colliding with something else
     * @return tower collision
     */
    public boolean getCollision(){
        return collision;
    }

    /**
     * Stes if tower is colliding with something else
     * @param set if tower is colliding
     */
    public void setCollision(Boolean set){
        collision = set;
    }

    /**
     * Sets a rectangle around tower, used for collision
     */
    public void setRectangle(){
        rectangle = new Rectangle();
        rectangle.setRect(this.x  , this.y  , this.width,this.height);
    }

    /**
     * Gets the rectangle around tower for collision
     * @return rectangle around tower
     */
    public Rectangle getRectangle(){
        return rectangle;
    }

    /**
     * Gets name of tower
     * @return name of tower
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the position of the tower
     * @param x The X-coordinate to set
     * @param y The Y-coordinate to set
     */
    public void setPos(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X-position of the tower
     * @return x-coordinate of tower
     */
    public float getPosX(){
        return x;
    }

    /**
     * Gets the Y-position of the tower
     * @return y-coordinate of tower
     */
    public float getPosY(){
        return y;
    }

    /**
     * Gets the height of tower
     * @return height of tower
     */
    public float getHeight(){
        return height;
    }

    /**
     * Gets the width of tower
     * @return width of tower
     */
    public float getWidth(){
        return width;
    }

    /**
     * Gets the angle of the tower
     * @return angle of tower
     */
    public double getAngle(){
        return angle;
    }

    /**
     * Sets the angle of the tower
     * @param setangle angle of tower to be set
     */
    public void setAngle(float setangle){
        if (isPlaced) { angle = setangle; }
    }

    /**
     * Gets the range of the tower
     * @return range of tower
     */
    public int getRange(){
        return range;
    }

    /**
     * Gets the current targetMode of tower
     * @return current targetMode
     */
    public ITargetMode getCurrentTargetMode() { return currentTargetMode; }

    public boolean GotTarget() {
        return gotTarget;
    }
    /**
     * Gets if tower is placed
     * @return if placed
     */


    public boolean isPlaced(){
        return isPlaced;
    }

    /**
     * Sets that tower is placed
     */
    public void placeTower(){
        isPlaced = true;
    }

    /**
     * Sets that tower has a target
     */
    public void haveTarget() { gotTarget = true; }

    /**
     * Sets that tower doesn't have a target
     */
    public void notHaveTarget() { gotTarget = false; }


}
