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


    private int health;


    private String spriteKey;

    private float xPos;
    private float yPos;



    private int widthX = 0;
    private int heightY = 0;

    private float totalDistanceTrawled = 0;

    private final Path path;
    private PositionVector currentMoveToVector;

    private int currentMoveToVectorIndex = 0;

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
        updateSpriteKey();
    }

    public void update() {
        moveToPoint();
    }

    private void moveToPoint() {
        double totalSpeed = (3F + health)/4F;

        double diffX = xPos + widthX / 2F - currentMoveToVector.getX();
        double diffY = yPos + heightY / 2F - currentMoveToVector.getY();

        double totalLengthToVector = Math.sqrt(Math.pow(diffX,2) + Math.pow(diffY,2));

        double lengthDiff = totalSpeed/totalLengthToVector;

        double addedDiffX = (diffX * lengthDiff);
        double addedDiffY = (diffY * lengthDiff);

        if (Double.isNaN(addedDiffX)) { addedDiffX = 0; }
        if (Double.isNaN(addedDiffY)) { addedDiffY = 0; }

        xPos -= addedDiffX;
        yPos -= addedDiffY;

        if (totalLengthToVector < totalSpeed) {
            currentMoveToVector = path.getWaypoint(currentMoveToVectorIndex++);
        }

        totalDistanceTrawled += totalSpeed;
    }

    private void updateSpriteKey() { spriteKey = "virus" + health; }

    public float getX() { return xPos; }

    public float getY() { return yPos; }

    public int getWidthX() {
        return widthX;
    }

    public int getHeightY() {
        return heightY;
    }

    public String getSpriteKey() { return spriteKey; }

    public float getTotalDistanceTrawled() { return totalDistanceTrawled; }

    public boolean isDead() {
        if(this.health == 0){
            return true;
        }
        return false;
    }
}
