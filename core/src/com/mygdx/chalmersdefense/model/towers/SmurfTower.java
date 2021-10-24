package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the SmurfTower
 */
final class SmurfTower extends Tower {

    /**
     * Creates object of a SmurfTower
     * @param x - startcoordinate of tower
     * @param y - startcoordinate of tower

     */
    SmurfTower(float x, float y) {
        super(x, y, "IT-Smurf", 120, 200, 200);
    }


    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createZeroOneProjectile(super.getX(), super.getY(), getAngle(), getUpgradeLevel()));
    }
}
