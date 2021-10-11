package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.HashMap;
import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the MechMiniTowers that MechTower creates
 * <p>
 * 2021-10-11 Modified by Joel Båtsman Hilmersson: Changed creation of minimech to only use public methods in Tower <br>
 */
class MechMiniTower extends Tower {

    MechMiniTower(float x, float y, int reloadSpeed, int range, List<ITargetMode> targetModes,ITargetMode currentTargetMode, int upgradeLevel) {
        super(x, y, "MechMini", reloadSpeed, 0, range, targetModes);

//        Om man hade bara kunnat ändra i super hade man kunnat skicka in dessa i reload och range
//        reloadSpeed - reloadSpeed * (Math.max(0, upgradeLevel-2) * 0.8)
//
//        range + range * (Math.max(0, upgradeLevel-2))

        for (int i = 0; i < targetModes.indexOf(currentTargetMode); i++){
            super.changeTargetMode(true);
        }

        if (upgradeLevel >= 2){
            HashMap<String, Double> upgrades = new HashMap<>();
            upgrades.put("attackSpeedMul", 1.0);
            upgrades.put("attackRangeMul", 1.0);
            super.upgradeTower(upgrades);
        }

        if(upgradeLevel == 3){
            HashMap<String, Double> upgrades = new HashMap<>();
            upgrades.put("attackSpeedMul",0.2);
            upgrades.put("attackRangeMul",2.0);
            super.upgradeTower(upgrades);
        }

    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createRobotProjectile(getX(), getY(), getAngle(), getUpgradeLevel()));
    }

}
