package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public interface ITower extends IMapObject {

    void update(List<IProjectile> projectilesList, List<ITower> towersList, float newAgle, boolean hasTarget,List<IVirus> viruses);

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

    int getRange();

    ITargetMode getCurrentTargetMode();

    boolean isPlaced();

    void placeTower();
}
