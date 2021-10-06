package com.mygdx.chalmersdefense.model.towers;


import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import javax.imageio.ImageIO;
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

abstract class Tower implements ITower{

    private String spriteKey;
    private int upgradeLevel = 1;

    private float angle = 0;
    private int range;
    private final String name;

    private boolean isPlaced = false;
    private float x;
    private float y;


    //private final TargetMode firstMode = TargetModeFactory.

    private final List<ITargetMode> targetModes;
    private final ITargetMode currentTargetMode;

    private float width;
    private float height;

    private final int cost;

    private boolean collision = false;


    private int reloadTime; //how many updates from model
    private int currentReload = 0;


    Tower(float x, float y, String name, int reloadTime, int cost, int range, List<ITargetMode> targetModes){
        this.name = name;
        this.reloadTime = reloadTime;
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
    }

    abstract void createProjectile(List<IProjectile> projectileList);

    @Override
    public void update(List<IProjectile> projectilesList, float newAngle, boolean hasTarget) {
        setAngle(newAngle);
        if(currentReload < 1 && hasTarget && isPlaced){
            currentReload = reloadTime;
            createProjectile(projectilesList);
        }
        else{
            currentReload --;
        }
    }



    public void upgradeTower(HashMap<String, Long> upgrades) {
        // DMG multiplier??
        reloadTime *= upgrades.get("attackSpeedMul");
        range *= upgrades.get("attackRangeMul");
        upgradeLevel++;
        updateSpriteKey(); // Add this when all sprites are in the game.
    }


    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    private void updateSpriteKey() { spriteKey = name + upgradeLevel; }


    public String getSpriteKey() {
        return spriteKey;
    }


    public int getCost() {
        return cost;
    }


    public boolean getCollision(){
        return collision;
    }


    public void setCollision(boolean set){
        collision = set;
    }


    public String getName(){
        return name;
    }


    public void setPos(float x, float y){
        this.x = x;
        this.y = y;
    }


    public float getX(){
        return x;
    }


    public float getY(){
        return y;
    }


    public float getHeight(){
        return height;
    }


    public float getWidth(){
        return width;
    }


    public float getAngle(){
        return angle;
    }

    /**
     * Sets the angle of the tower
     * @param newAngle angle of tower to be set
     */
    void setAngle(float newAngle) { if (isPlaced && (newAngle >= 0)){ angle = newAngle; }}

    public int getRange(){
        return range;
    }

    public ITargetMode getCurrentTargetMode() { return currentTargetMode; }

    public boolean isPlaced(){
        return isPlaced;
    }

    public void placeTower(){
        isPlaced = true;
    }

}
