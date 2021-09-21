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
        sprite.setX(sprite.getX() + (4F + health)/5F);
    }

    private void moveToPoint(){

    }

}
