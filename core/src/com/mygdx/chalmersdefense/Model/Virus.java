package com.mygdx.chalmersdefense.Model;

import com.mygdx.chalmersdefense.Model.CustomExceptions.NoFurtherWaypointException;
import com.mygdx.chalmersdefense.Model.Path.Path;
import com.mygdx.chalmersdefense.Utilities.PositionVector;

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


    private final Path path;
    private PositionVector currentMoveToVector;

    private int currentMoveToVectorIndex = 0;

    public Virus(int health, Path path) {
        this.health = health;
        updateImagePath();

        this.path = path;
        currentMoveToVector = path.getFirstWaypoint();
        //sprite.setPosition(currentMoveToVector.getX() - sprite.getWidth()/2, currentMoveToVector.getY() - sprite.getHeight()/2);
        xPos = currentMoveToVector.getX();
        yPos = currentMoveToVector.getY();

    }


    public void update() {
        moveToPoint();
    }

    private void moveToPoint() {
        double totalSpeed = (3F + health)/4F;
//        double diffX = xPos + sprite.getWidth()/2 - currentMoveToVector.getX();
//        double diffY = yPos + sprite.getHeight()/2 - currentMoveToVector.getY();
        double diffX = xPos - currentMoveToVector.getX();
        double diffY = yPos - currentMoveToVector.getY();

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
    }

    private void updateImagePath() { imagePath = "virus" + health + "Hp.png"; }

    public float getX() { return xPos; }

    public float getY() { return yPos; }

    public String getImagePath() { return imagePath; }

}
