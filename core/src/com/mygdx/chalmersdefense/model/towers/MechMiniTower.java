package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the MechMiniTowers that MechTower creates
 */
class MechMiniTower extends Tower {
    MechMiniTower(float x, float y, int reloadSpeed, int range, List<ITargetMode> targetModes) {
        super(x, y, "MechMini", reloadSpeed, 0, range, targetModes);
    }


    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createRobotProjectile(getX(), getY(), getAngle(), getUpgradeLevel()));
    }
}
