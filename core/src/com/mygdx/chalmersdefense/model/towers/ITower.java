package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.awt.*;
import java.util.HashMap;

public interface ITower extends IMapObject {

    void update();

    Projectile shootProjectile();

    void upgradeTower(HashMap<String, Long> upgrades);

    int getUpgradeLevel();


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

    void setAngle(float setAngle);

    int getRange();

    ITargetMode getCurrentTargetMode();

    boolean isPlaced();

    void placeTower();

    void haveTarget();

    void notHaveTarget();


}
