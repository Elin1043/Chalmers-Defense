package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the ElectroTower
 */
final class ElectroTower extends Tower {


    ElectroTower(float x, float y, String name, int reloadSpeed, int cost, int range) {
        super(x, y, name, reloadSpeed, cost, range);
    }


    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createLightningProjectile(getX(), getY(), getAngle(), getUpgradeLevel()));
    }


}
