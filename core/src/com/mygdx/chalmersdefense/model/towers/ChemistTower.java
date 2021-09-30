package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.AcidPool;
import com.mygdx.chalmersdefense.model.projectiles.AcidProjectile;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

public class ChemistTower extends Tower{

    public ChemistTower(float x, float y, String name, int attackSpeed, int cost, int range, List<ITargetMode> targetModes) {
        super(x, y, name, attackSpeed, cost, range, targetModes);
    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(new AcidProjectile(super.getX(), super.getY(), super.getAngle(), projectileList, super.getUpgradeLevel()));
    }
}
