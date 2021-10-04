package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;


import java.awt.*;
import java.util.HashMap;
import java.util.List;

/**
 * @author Elin Forsberg
 * Interface for towers
 */
public interface ITower extends IMapObject {

    void update(List<IProjectile> projectilesList, float newAgle, boolean hasTarget);

    void upgradeTower(HashMap<String, Long> upgrades);

    int getUpgradeLevel();

    String getSpriteKey();

    int getCost();

    boolean getCollision();

    void setCollision(boolean set);

    String getName();

    void setPos(float x, float y);

    int getRange();

    ITargetMode getCurrentTargetMode();

    boolean isPlaced();

    void placeTower();
}
