package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the ElectroTower
 */
final class ElectroTower extends Tower {

    /**
     * Creates object of a ElectroTower
     *
     * @param x - startcoordinate of tower
     * @param y - startcoordinate of tower
     */
    ElectroTower(float x, float y) {
        super(x, y, "Electroman", 300, 400, 200);
    }


    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createLightningProjectile(getX(), getY(), getAngle(), getUpgradeLevel()));
    }


}
