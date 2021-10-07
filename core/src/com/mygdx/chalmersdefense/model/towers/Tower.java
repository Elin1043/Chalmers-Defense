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
 * <p>
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: changed class to hold hashmap key for sprite
 * 2021-09-25 Modified by Elin Forsberg: added method for shooting projectiles
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

    private final ITargetMode currentTargetMode;    // Which current targeting mode to use

    private float width;            // Width of tower object
    private float height;           // Height of tower object

    private final int cost;         // Cost of tower

    private boolean collision = false;  // When tower is placed, this helps model to know if tower collides with anything


    private int reloadTime;         // Reload time of tower. (How many update cycles before tower will shoot)
    private int currentReload = 0;  // Current reload


    Tower(float x, float y, String name, int reloadTime, int cost, int range, List<ITargetMode> targetModes) {
        this.name = name;
        this.reloadTime = reloadTime;
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
     * @param projectileList the list of which the projectile should be added too
     */
    abstract void createProjectile(List<IProjectile> projectileList);

    @Override
    public void update(List<IProjectile> projectilesList, float newAngle, boolean hasTarget) {
        setAngle(newAngle);
        if (currentReload < 1 && hasTarget && isPlaced) {
            currentReload = reloadTime;
            createProjectile(projectilesList);
        } else {
            currentReload--;
        }
    }


    @Override
    public void upgradeTower(HashMap<String, Long> upgrades) {
        reloadTime *= upgrades.get("attackSpeedMul");
        range *= upgrades.get("attackRangeMul");
        upgradeLevel++;
        updateSpriteKey(); // Add this when all sprites are in the game.
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
    public boolean getCollision() {
        return collision;
    }

    @Override
    public void setCollision(boolean set) {
        collision = set;
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

}
