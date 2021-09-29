package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.awt.*;
import java.util.HashMap;

public interface ITower extends IMapObject {

    void update();

    void shootProjectile();

    void upgradeTower(HashMap<String, Long> upgrades);

    int getUpgradeLevel();

    void updateSpriteKey();

    String getSpriteKey();

    int getCost();

    boolean getGotButton();

    void setGotButton(boolean gotButton);

    boolean getCollision();

    void setCollision(boolean set);

    void setRectangle();

    Rectangle getRectangle();

    String getName();

    void setPos(float x, float y);

    float getPosX();

    float getPosY();

    float getHeight();

    float getWidth();

    void setAngle(float setAngle);

    int getRange();

    ITargetMode getCurrentTargetMode();

    boolean isPlaced();

    void placeTower();

    void haveTarget();

    void notHaveTarget();


}
