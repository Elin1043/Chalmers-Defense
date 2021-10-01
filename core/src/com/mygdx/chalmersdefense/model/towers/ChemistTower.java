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

    public ChemistTower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes) {
        super(x, y, name, attackSpeed, cost, range, targetModes);
    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createAcidProjectile(getX(), getY(), getAngle(), getUpgradeLevel(), projectileList));
    }
}
