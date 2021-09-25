package com.mygdx.chalmersdefense.model;

import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.utilities.PositionVector;
import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.utilities.PositionVector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Joel Båtsman Hilmersson
 * A class that representates the common enemy type for the game
 */
public class Virus {
    private int health; // Current health of virus

    private String spriteKey;   // The key to the Sprite Hashmap

    private float xPos;         // x position on map
    private float yPos;         // y position on map

    private int widthX = 0;     // width of object
    private int heightY = 0;    // height of object

    private float totalDistanceTrawled = 0;     // Total distance the virus have trawled

    private final Path path;    // pointer to path object
    private PositionVector currentMoveToVector;     // Current vector (coordinates) to move to
    private int currentMoveToVectorIndex = 0;       // Which index to use when new vector is retrieved

    /**
     * Creates Virus object
     * @param health Amount of health the virus start with
     * @param path  The path to follow
     */
    public Virus(int health, Path path) {
        this.health = health;
        updateSpriteKey();
        this.path = path;

        currentMoveToVector = path.getWaypoint(currentMoveToVectorIndex);


        // Kanske vill göra detta när man ändrar liv. Iallafall om man har något virus av annan storlek
        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("viruses/virus" + health + "Hp.png")));
            this.widthX = img.getWidth();
            this.heightY = img.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }

        xPos = currentMoveToVector.getX() - widthX / 2F;
        yPos = currentMoveToVector.getY() - heightY / 2F;
    }

    public void decreaseHealth() {
        this.health --;
        if (health > 0) {
            updateSpriteKey();
        }
    }

    /**
     * Updates position of virus
     */
    public void update() {
        moveToPoint();
    }

    private void moveToPoint() {
        double totalSpeed = (3F + health)/4F;      // Calculates speed based on health of virus

        // Gets length to next move to point
        double diffX = xPos + widthX / 2F - currentMoveToVector.getX();
        double diffY = yPos + heightY / 2F - currentMoveToVector.getY();

        double totalLengthToVector = Math.sqrt(Math.pow(diffX,2) + Math.pow(diffY,2));

        // Gets ratio of speed to length
        double lengthDiff = totalSpeed/totalLengthToVector;

        // Multiplies this to get how much in x and y
        double addedDiffX = (diffX * lengthDiff);
        double addedDiffY = (diffY * lengthDiff);

        if (Double.isNaN(addedDiffX)) { addedDiffX = 0; }
        if (Double.isNaN(addedDiffY)) { addedDiffY = 0; }

        // Subtract this from current position
        xPos -= addedDiffX;
        yPos -= addedDiffY;

        if (totalLengthToVector < totalSpeed) {
            currentMoveToVector = path.getWaypoint(currentMoveToVectorIndex++);
        }

        totalDistanceTrawled += totalSpeed;
    }

    private void updateSpriteKey() { spriteKey = "virus" + health; } // Updates the key to Sprite hashmap

    /**
     * Gets Virus x position
     * @return Virus x position
     */
    public float getX() { return xPos; }

    /**
     * Gets Virus y position
     * @return Virus y position
     */
    public float getY() { return yPos; }

    /**
     * Gets the key to Sprite hashmap for rendering
     * @return Key to hashmap
     */
    public String getSpriteKey() { return spriteKey; }

    /**
     * Gets the amount of damage the virus does when reaching end of path
     * @return Amount of damage to be done
     */
    public int getLifeDecreaseAmount() { return health; }

    /**
     * Gets the total distance trawled by the virus object
     * @return Total distance trawled
     */
    public float getTotalDistanceTrawled() { return totalDistanceTrawled; }

    public boolean isDead() {
        return this.health <= 0;
    }
}
