package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.HashMap;
import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the MechMiniTowers that MechTower creates
 */
class MechMiniTower extends Tower {

    private HashMap<String, Double> upgrades = new HashMap<>();  //Hashmap that contains the information of upgrades
    private int reloadSpeed;                                     //reloadSpeed of the tower
    private int range;                                           //range of the tower

    MechMiniTower(float x, float y, int reloadSpeed, int range, List<ITargetMode> targetModes,ITargetMode currentTargetMode, int upgradeLevel) {
        super(x, y, "MechMini", reloadSpeed, 0, range, targetModes);
        this.reloadSpeed = reloadSpeed;
        this.range = range;
        this.currentTargetMode = currentTargetMode;

        if(upgradeLevel == 3){
            upgrades.put("attackSpeedMul",0.2);
            upgrades.put("attackRangeMul",2.0);
            super.upgradeTower(upgrades);
            this.upgradeLevel = 3;
        }

        this.updateSpriteKey();
    }

    @Override
    public void upgradeTower(HashMap<String, Double> upgrades) {
        reloadSpeed *= upgrades.get("attackSpeedMul") ;
        range *= upgrades.get("attackRangeMul");
        upgradeLevel++;
    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createRobotProjectile(getX(), getY(), getAngle(), getUpgradeLevel()));
    }

}
