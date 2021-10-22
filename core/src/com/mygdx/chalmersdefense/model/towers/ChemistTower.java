package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the ChemistTower
 * <p>
 * 2021-09-30 Modified by Joel Båtsman Hilmersson: Changed to use ProjectileFactory when creating projectile
 */
final class ChemistTower extends Tower {
    private final List<IProjectile> addToList;  // List to add new projectiles to


    /**
     * Creates object of a ChemistTower
     * @param x - startcoordinate of tower
     * @param y - startcoordinate of tower
     * @param name of the tower
     * @param reloadSpeed of the tower
     * @param cost of the tower
     * @param range of the tower
     * @param addToList list to add towers projectiles to
     */
    ChemistTower(float x, float y, String name, int reloadSpeed, int cost, int range, List<IProjectile> addToList) {
        super(x, y, name, reloadSpeed, cost, range);
        this.addToList = addToList;
    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createAcidProjectile(getX(), getY(), getAngle(), getUpgradeLevel(), addToList));
    }
}
