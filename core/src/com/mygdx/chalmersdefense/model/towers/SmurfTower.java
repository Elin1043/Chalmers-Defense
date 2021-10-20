package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the SmurfTower
 */
final class SmurfTower extends Tower {
    SmurfTower(float x, float y, String name, int reloadSpeed, int cost, int range, List<ITargetMode> targetModes) {
        super(x, y, name, reloadSpeed, cost, range, targetModes);
    }


    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createZeroOneProjectile(super.getX(), super.getY(), getAngle(), getUpgradeLevel()));
    }
}
