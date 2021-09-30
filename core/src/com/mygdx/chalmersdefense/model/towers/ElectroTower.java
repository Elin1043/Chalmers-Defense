package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

public class ElectroTower extends Tower{
    public ElectroTower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes, IProjectile projectile) {
        super(x, y, name, attackSpeed, cost, range, targetModes, projectile);
    }
}
