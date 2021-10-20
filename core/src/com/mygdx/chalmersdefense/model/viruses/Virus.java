package com.mygdx.chalmersdefense.model.viruses;

import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.utilities.Calculate;
import com.mygdx.chalmersdefense.utilities.PositionVector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Joel Båtsman Hilmersson
 * A class that representates the common enemy type for the game
 * <p>
 * 2021-09-24 Modified by Elin Forsberg: Added methods to decrease health of virus and check if it's dead
 * 2021-10-19 Modified by Elin Forsberg: Added another constructor to be used by the BossVirus
 */
abstract class Virus implements IVirus {
    private int health; // Current health of virus

    private String spriteKey;   // The key to the Sprite Hashmap

    private float xPos;         // x position on map
    private float yPos;         // y position on map


    private float widthX = 0;     // width of object
    private float heightY = 0;    // height of object

    private float totalDistanceTrawled = 0;     // Total distance the virus have trawled

    private float slowdown = 1;                 // Amount of slow down that gets applied to the virus speed
    private int slowDownTimer = 0;

    private final Path path;    // pointer to path object
    private PositionVector currentMoveToVector;     // Current vector (coordinates) to move to
    private int currentMoveToVectorIndex = 0;       // Which index to use when new vector is retrieved


    /**
     * Creates Virus object
     *
     * @param health Amount of health the virus start with
     * @param path   The path to follow
     */
    Virus(int health, Path path) {
        this.health = health;
        this.path = path;
        initializeVirus();

        xPos = currentMoveToVector.getX() - widthX / 2F;
        yPos = currentMoveToVector.getY() - heightY / 2F;

    }

    /**
     * Creates a virus object with specific values
     * @param health health of the virus
     * @param path  path to be used by virus
     * @param x   x-coordinate of virus
     * @param y   y-coordinate of virus
     * @param currentMoveToVectorIndex  vectorIndex virus should walk towards
     */
    Virus(int health, Path path,float x, float y, int currentMoveToVectorIndex) {
        this.health = health;
        this.path = path;
        this.currentMoveToVectorIndex = currentMoveToVectorIndex;
        initializeVirus();

        float[] randomPoint = Calculate.randPoint(x, y,50);

        xPos = randomPoint[0] - widthX / 2F;
        yPos = randomPoint[1] - heightY / 2F;
    }


    private void initializeVirus(){
        updateSpriteKey();

        currentMoveToVector = path.getWaypoint(currentMoveToVectorIndex);

        // TODO Kanske vill göra detta när man ändrar liv. Iallafall om man har något virus av annan storlek
        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("viruses/virus" + health + "Hp.png")));
            this.widthX = img.getWidth();
            this.heightY = img.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void decreaseHealth(float damage) {
        if (damage < 1 && damage > 0.1F){
            slowDownEffect(damage);
        } else {
            this.health -= damage;
        }
    }

    /**
     * Update the spriteKey if health > 0
     */
    void seeIfUpdateSpriteKey() {
        if (health > 0) {
            updateSpriteKey();
        } else {
            health = 0;
        }
    }

    /**
     * Set how much slowndown to be set on virus
     * @param slowdown amount of slowdown
     */
    void slowDownEffect(float slowdown){
        if (this.slowdown > slowdown) {
            this.slowdown = slowdown;
        }
        slowDownTimer = 600;
    }


    @Override
    public void update() {
        moveToPoint(getTotalVirusSpeed());
        updateSlowTimer();
    }

    /**
     * Get the total speed of the virus
     * @return total speed
     */
    double getTotalVirusSpeed() {
        return ((3F + health) / 4F) * slowdown;
    }

    private void moveToPoint(double totalSpeed) {

        // Gets length to next move to point
        double diffX = xPos + widthX / 2F - currentMoveToVector.getX();
        double diffY = yPos + heightY / 2F - currentMoveToVector.getY();

        double totalLengthToVector = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

        // Gets ratio of speed to length
        double lengthDiff = totalSpeed / totalLengthToVector;

        // Multiplies this to get how much in x and y
        double addedDiffX = (diffX * lengthDiff);
        double addedDiffY = (diffY * lengthDiff);

        if (Double.isNaN(addedDiffX)) {
            addedDiffX = 0;
        }
        if (Double.isNaN(addedDiffY)) {
            addedDiffY = 0;
        }

        // Subtract this from current position
        xPos -= addedDiffX;
        yPos -= addedDiffY;

        if (totalLengthToVector < totalSpeed) {
            currentMoveToVector = path.getWaypoint(currentMoveToVectorIndex++);
        }

        totalDistanceTrawled += totalSpeed;
    }

    private void updateSlowTimer(){
        if (slowDownTimer <= 0){
            slowdown = 1;
        } else {
            slowDownTimer--;
        }
    }

    private void updateSpriteKey() {
        spriteKey = "virus" + health;
    } // Updates the key to Sprite hashmap

    /**
     * Gets Virus x position
     *
     * @return Virus x position
     */
    public float getX() {
        return xPos;
    }

    /**
     * Gets Virus y position
     *
     * @return Virus y position
     */
    public float getY() {
        return yPos;
    }

    /**
     * Gets width of virus
     *
     * @return width
     */
    public float getWidth() {
        return widthX;
    }

    /**
     * Gets height of virus
     *
     * @return height
     */
    public float getHeight() {
        return heightY;
    }

    /**
     * Gets the key to Sprite hashmap for rendering
     *
     * @return Key to hashmap
     */
    public String getSpriteKey() {
        return spriteKey;
    }

    @Override
    public float getAngle() {
        return 0;
    }

    /**
     * Gets the amount of damage the virus does when reaching end of path
     *
     * @return Amount of damage to be done
     */
    public int getLifeDecreaseAmount() {
        return health;
    }

    /**
     * Gets the total distance trawled by the virus object
     *
     * @return Total distance trawled
     */
    @Override
    public float getTotalDistanceTraveled() {
        return totalDistanceTrawled;
    }

    /**
     * Gets if virus health is 0, which means it's dead
     *
     * @return if health is 0
     */
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Returns slowdown
     * @return slowdown
     */
    float getSlowdown(){ return slowdown; }

    /**
     * Gets the position of the virus
     * @return Position of virus
     */
    float[] getPos(){ return new float[] {xPos, yPos}; }

    /**
     * Gets the current move to vector
     * @return the move to vector
     */
    int getCurrentMoveToVectorIndex(){ return currentMoveToVectorIndex - 1; }
}
