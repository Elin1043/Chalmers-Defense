package com.mygdx.chalmersdefense.model.towers;


import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
import com.mygdx.chalmersdefense.model.targetMode.TargetModeFactory;
import com.mygdx.chalmersdefense.model.modelUtilities.CountDownTimer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * @author Elin Forsberg
 * A class defining the tower objects
 * <p>
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: changed class to hold hashmap key for sprite <br>
 * 2021-09-25 Modified by Elin Forsberg: added method for shooting projectiles <br>
 */
abstract class Tower implements ITower {

    private String spriteKey;       // The key to the Sprite Hashmap
    private int upgradeLevel = 1;   // The current upgradeLevel

    private float angle = 0;        // Current angle of tower
    private int range;              // Current shooting range of tower
    private final String name;      // Tower Name

    private boolean isPlaced = false;   // Boolean over if this tower is placed
    private float x;                // X coordinate on map
    private float y;                // y coordinate on map

    private final List<ITargetMode> targetModes = TargetModeFactory.getTargetModes();    // List that holds references to the targetmodes
    private ITargetMode currentTargetMode;    // Which current targeting mode to use

    private float width;            // Width of tower object
    private float height;           // Height of tower object

    private final int cost;         // Cost of tower

    private boolean canRemove = false;  // This variable is set when the tower should be removed

    private CountDownTimer reloadTimer;     // Reload timer of tower. (How many update cycles before tower will shoot)
    private int reloadTime;                 // Variable to calculate new reload time when upgrading


    /**
     * Creates object of a Tower
     *
     * @param x           - startcoordinate of tower
     * @param y           - startcoordinate of tower
     * @param name        of the tower
     * @param reloadSpeed of the tower
     * @param cost        of the tower
     * @param range       of the tower
     */
    Tower(float x, float y, String name, int reloadSpeed, int cost, int range) {
        this.name = name;
        this.reloadTime = reloadSpeed;
        this.reloadTimer = new CountDownTimer(reloadSpeed, 0);
        this.currentTargetMode = targetModes.get(0);
        updateSpriteKey();

        try {
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("towers/" + name + "/" + spriteKey + ".png")));
            this.width = towerImage.getWidth();
            this.height = towerImage.getHeight();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        this.setPos(x, y);
        this.range = range;
        this.cost = cost;
    }

    /**
     * Create a new projectile
     *
     * @param projectileList the list of which the projectile should be added too
     */
    abstract void createProjectile(List<IProjectile> projectileList);

    @Override
    public void update(List<IProjectile> projectilesList, float newAngle, boolean hasTarget) {
        setAngle(newAngle);

        updateReloadTimer();

        if (hasTarget && isPlaced && reloadTimer.getCurrentCountTime() <= 0) { // Here we only look to see if timer is 0, Not count down
            reloadTimer.haveReachedZero();          // Need to reset the timer for next countdown
            createProjectile(projectilesList);
        }
    }


    //Update the reload timer
    private void updateReloadTimer() {
        if (reloadTimer.getCurrentCountTime() > 0) { // If the timer is over 0 it should count down
            reloadTimer.haveReachedZero();
        }
    }

    @Override
    public void changeTargetMode(boolean goRight) {
        if (goRight) {
            increaseIndexOfTargetMode();
        } else {
            decreaseIndexOfTargetMode();
        }
    }

    //Increase the index of targetMode
    private void increaseIndexOfTargetMode() {
        if (targetModes.indexOf(currentTargetMode) >= targetModes.size() - 1) {
            currentTargetMode = targetModes.get(0);
        } else {
            currentTargetMode = targetModes.get(targetModes.indexOf(currentTargetMode) + 1);
        }
    }

    //Decrease the index of targetMode
    private void decreaseIndexOfTargetMode() {
        if (targetModes.indexOf(currentTargetMode) <= 0) {
            currentTargetMode = targetModes.get(targetModes.size() - 1);
        } else {
            currentTargetMode = targetModes.get(targetModes.indexOf(currentTargetMode) - 1);
        }
    }

    @Override
    public void upgradeTower(HashMap<String, Double> upgrades) {
        reloadTime *= upgrades.get("attackSpeedMul");

        // Creates new timer object with new timer
        reloadTimer = new CountDownTimer(reloadTime, reloadTimer.getCurrentCountTime());

        range *= upgrades.get("attackRangeMul");
        upgradeLevel++;
        updateSpriteKey();
    }

    @Override
    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    /**
     * Update the sprite key
     */
    private void updateSpriteKey() {
        spriteKey = name + upgradeLevel;
    }

    @Override
    public String getSpriteKey() {
        return spriteKey;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public boolean canRemove() {
        return canRemove;
    }

    @Override
    public void setIfCanRemove(boolean set) {
        canRemove = set;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    /**
     * Sets the angle of the tower
     *
     * @param newAngle angle of tower to be set
     */
    void setAngle(float newAngle) {
        if (isPlaced && (newAngle >= 0)) {
            angle = newAngle;
        }
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public ITargetMode getCurrentTargetMode() {
        return currentTargetMode;
    }

    @Override
    public boolean isPlaced() {
        return isPlaced;
    }

    @Override
    public void placeTower() {
        isPlaced = true;
    }

    /**
     * Returns the current targetmode index in the targetmode list
     *
     * @return the current targetmode index
     */
    int getCurrentTargetModeIndex() {
        return targetModes.indexOf(currentTargetMode);
    }

}
