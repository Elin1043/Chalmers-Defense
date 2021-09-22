package com.mygdx.chalmersdefense.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.chalmersdefense.Model.Path.Path;
import com.mygdx.chalmersdefense.Utilities.PositionVector;

/**
 * @author Joel Båtsman Hilmersson
 * A class that representates the common enemy type for the game
 */
public class Virus {
    private int health;
    private Sprite sprite;
    private final Path path;
    private PositionVector currentMoveToVector;

    int currentMoveToVectorIndex = 0;

    public Virus(int health, Sprite sprite, Path path) {
        this.health = health;
        this.sprite = sprite;
        this.path = path;
        currentMoveToVector = path.getFirstWaypoint();
        sprite.setPosition(currentMoveToVector.getX() - sprite.getWidth()/2, currentMoveToVector.getY() - sprite.getHeight()/2);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void update() {
        moveToPoint();
    }

    private void moveToPoint() {
        int totalSpeed = (3 + health)/4;
        double diffX = sprite.getX() + sprite.getWidth() - currentMoveToVector.getX();
        double diffY = sprite.getY() + sprite.getHeight() - currentMoveToVector.getY();

        //System.out.println(diffY);

        double totalLengthToVector = Math.sqrt(Math.pow(diffX,2) + Math.pow(diffY,2));

        double lengthDiff = totalSpeed/totalLengthToVector;

        //System.out.println(lengthDiff);

        //System.out.println((diffY * lengthDiff));
        //System.out.println((float) (sprite.getY() - (diffY * lengthDiff)));

        double addedDiffX = (diffX * lengthDiff);
        double addedDiffY = (diffY * lengthDiff);

        if (Double.isNaN(addedDiffX)) { addedDiffX = 0; }
        if (Double.isNaN(addedDiffY)) { addedDiffY = 0; }

        sprite.setPosition((float) (sprite.getX() - addedDiffX), (float) (sprite.getY() - addedDiffY));



        if (totalLengthToVector < totalSpeed) {
            currentMoveToVector = path.getWaypoint(currentMoveToVectorIndex++);
        }

    }

}
