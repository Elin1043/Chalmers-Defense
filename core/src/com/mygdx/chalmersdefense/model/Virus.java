package com.mygdx.chalmersdefense.model;

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
    //private final Sprite sprite;


    private String imagePath;

    private float xPos;
    private float yPos;

    private int witdhX = 0;
    private int heightY = 0;

    private double totalLengthToVector;


    private final Path path;
    private PositionVector currentMoveToVector;

    private int currentMoveToVectorIndex = 0;

    public Virus(int health, Path path) {
        this.health = health;
        updateImagePath();

        this.path = path;
        currentMoveToVector = path.getFirstWaypoint();
        //sprite.setPosition(currentMoveToVector.getX() - sprite.getWidth()/2, currentMoveToVector.getY() - sprite.getHeight()/2);


        //xPos = currentMoveToVector.getX();
        //yPos = currentMoveToVector.getY();

        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath)));
            this.witdhX = img.getWidth();
            this.heightY = img.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }

        xPos = currentMoveToVector.getX() - witdhX / 2F;
        yPos = currentMoveToVector.getY() - heightY / 2F;
    }


    public void update() {
        moveToPoint();
    }

    private void moveToPoint() {
        double totalSpeed = (3F + health)/4F;
//        double diffX = xPos + sprite.getWidth()/2 - currentMoveToVector.getX();
//        double diffY = yPos + sprite.getHeight()/2 - currentMoveToVector.getY();
        double diffX = xPos + witdhX / 2F - currentMoveToVector.getX();
        double diffY = yPos + heightY / 2F - currentMoveToVector.getY();

        totalLengthToVector = Math.sqrt(Math.pow(diffX,2) + Math.pow(diffY,2));

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
    }

    public double getDistanceToNextPoint(){
        return totalLengthToVector;
    }

    public int getWayPointIndex(){
        return currentMoveToVectorIndex;
    }

    private void updateImagePath() { imagePath = "virus" + health + "Hp.png"; }

    public float getX() { return xPos; }

    public float getY() { return yPos; }

    public String getImagePath() { return imagePath; }

}
