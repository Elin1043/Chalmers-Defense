package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the ElectroTower
 */
public class ElectroTower extends Tower{


    public ElectroTower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes) {
        super(x, y, name, attackSpeed, cost, range, targetModes);
    }


    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createLightningProjectile(getX(), getY(), getAngle(), getUpgradeLevel()));
    }


}
