package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.*;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the ChemistTower
 *
 * 2021-09-30 Modified by Joel Båtsman Hilmersson: Changed to use ProjectileFactory when creating projectile
 */
public class ChemistTower extends Tower{
    private final List<IProjectile> addToList;

    public ChemistTower(float x, float y, String name, int reloadSpeed, int cost, int range, List<ITargetMode> targetModes, List<IProjectile> addToList) {
        super(x, y, name, reloadSpeed, cost, range, targetModes);
        this.addToList = addToList;
    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createAcidProjectile(getX(), getY(), getAngle(), getUpgradeLevel(), addToList));
    }
}
