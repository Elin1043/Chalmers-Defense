package com.mygdx.chalmersdefense.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Virus {
    private int health;
    private Sprite sprite;

    public Virus(int health, Sprite sprite) {
        this.health = health;
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void update() {
        sprite.setX(sprite.getX() + 1);
    }

}
