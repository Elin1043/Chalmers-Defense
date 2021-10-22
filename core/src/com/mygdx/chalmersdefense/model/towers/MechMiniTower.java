package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
import com.mygdx.chalmersdefense.utilities.CountDownTimer;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author Elin Forsberg
 * Class representing the MechMiniTowers that MechTower creates
 * <p>
 * 2021-10-11 Modified by Joel Båtsman Hilmersson: Changed creation of minimech to only use public methods in Tower <br>
 */
final class MechMiniTower extends Tower {

    private final CountDownTimer lifeTimeCounter;   // The life timer of the MiniMechTower

    /**
     * Creates object of a MechMiniTower
     * @param x - startcoordinate of tower
     * @param y - startcoordinate of tower
     * @param reloadSpeed of the tower
     * @param range of the tower
     * @param currentTargetModeIndex targetModeIndex for getting targetMode
     * @param upgradeLevel upgradelevel to be set
     */
    MechMiniTower(float x, float y, int reloadSpeed, int range, int currentTargetModeIndex, int upgradeLevel) {
        super(x, y, "MechMini", reloadSpeed, 0, range);

        Random rand = new Random();
        this.lifeTimeCounter = new CountDownTimer(rand.nextInt(401) + 800);

        for (int i = 0; i < currentTargetModeIndex; i++){
            super.changeTargetMode(true);
        }

        HashMap<String, Double> upgrades = new HashMap<>();
        upgrades.put("attackSpeedMul", 1.0);
        upgrades.put("attackRangeMul", 1.0);
        super.upgradeTower(upgrades);


        if(upgradeLevel == 3){
            upgrades.clear();
            upgrades.put("attackSpeedMul",1.1);
            upgrades.put("attackRangeMul",2.0);
            super.upgradeTower(upgrades);
        }

    }

    @Override
    public void update(List<IProjectile> projectilesList, float newAngle, boolean hasTarget) {
        if (lifeTimeCounter.haveReachedZero()) { setIfCanRemove(true); }
        super.update(projectilesList, newAngle, hasTarget);
    }

    @Override
    public void upgradeTower(HashMap<String, Double> upgrades) {}

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createRobotProjectile(getX(), getY(), getAngle(), getUpgradeLevel()));
    }

}
