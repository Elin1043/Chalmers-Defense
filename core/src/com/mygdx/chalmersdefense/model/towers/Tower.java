package com.mygdx.chalmersdefense.model.towers;


import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * @author Elin Forsberg
 * A class defining the tower objects
 *
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: changed class to hold hashmap key for sprite
 * 2021-09-25 Modified by Elin Forsberg: added method for shooting projectiles
 */

public abstract class Tower implements ITower{

    private String spriteKey;
    private int upgradeLevel = 1;

    private float angle = 0;
    private int range;
    private String name;

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


    private int reloadTime = 60*3; //how many updates from model
    private int currentReload = 0;


    private Rectangle rectangle = new Rectangle();


    public Tower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes){
        this.name = name;
        this.attackSpeed = attackSpeed;
        this.targetModes = targetModes;
        this.currentTargetMode = targetModes.get(0);
        updateSpriteKey();

        try{
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("towers/" + name + "/" + spriteKey + ".png")));
            this.width         = towerImage.getWidth();
            this.height        = towerImage.getHeight();


        }
        catch (IOException exception){
            exception.printStackTrace();
        }

        this.setPos(x,y);
        this.range = range;
        this.cost = cost;
        this.collision = false;
        this.gotButton = false;


    }

    abstract void createProjectile(List<IProjectile> projectileList);

    @Override
    public void update(List<IProjectile> projectilesList, float newAngle, boolean hasTarget) {
        setAngle(newAngle);
        if(currentReload < 1 && hasTarget && isPlaced){
            currentReload = reloadTime;
            //for (IVirus virus : viruses) {virus.setGotHit(false);}
            createProjectile(projectilesList);
        }
        else{
            currentReload --;
        }
    }


    /**
     * Upgrades the tower based on given HashMap with upgrade values
     * @param upgrades a HashMap with upgrade values.
     */
    public void upgradeTower(HashMap<String, Long> upgrades) {
        // DMG multiplier??
        attackSpeed *= upgrades.get("attackSpeedMul");
        range *= upgrades.get("attackRangeMul");
        upgradeLevel++;
        updateSpriteKey(); // Add this when all sprites are in the game.
    }

    /**
     * Get the upgrade level of tower
     * @return upgrade level
     */
    public int getUpgradeLevel() {
        return upgradeLevel;
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
     * Sets if tower is colliding with something else
     * @param set if tower is colliding
     */
    public void setCollision(boolean set){
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
    public float getX(){
        return x;
    }

    /**
     * Gets the Y-position of the tower
     * @return y-coordinate of tower
     */
    public float getY(){
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
    public float getAngle(){
        return angle;
    }

    /**
     * Sets the angle of the tower
     * @param newAngle angle of tower to be set
     */
    void setAngle(float newAngle) { if (isPlaced && (newAngle >= 0)){ angle = newAngle; }}

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

}
