package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

public class SmurfTower extends Tower{
    public SmurfTower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes, Projectile projectile) {
        super(x, y, name, attackSpeed, cost, range, targetModes, projectile);
    }
}
