package com.mygdx.chalmersdefense.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.utilities.PositionVector;

/**
 * @author Joel Båtsman Hilmersson
 * A class that representates the common enemy type for the game
 */
public class Virus {
    private int health;
    private Sprite sprite;
    private final Path path;
    private final PositionVector startPos;

    public Virus(int health, Sprite sprite, Path path) {
        this.health = health;
        this.sprite = sprite;
        this.path = path;
        startPos = path.getFirstWaypoint();
        sprite.setPosition(startPos.getX(), startPos.getY());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void update() {
        sprite.setX(sprite.getX() + (4F + health)/5F);
    }

}
