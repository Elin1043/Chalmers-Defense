package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the MechTower
 */
class MechTower extends Tower {

    private final List<ITower> miniTowers = new ArrayList<>();  // List to add minitower to
    private final int reloadSpeed;      // Reload speed
    private final int range;            // Current range of tower, this will be passed to minimechtowers later
    private final List<ITargetMode> targetModes;    // All possible targeted
    List<ITower> towersToAddList;       // The final list to add towers to get them to show up on the map


    MechTower(float x, float y, String name, int reloadSpeed, int cost, int range, List<ITargetMode> targetModes, List<ITower> towersToAddList) {
        super(x, y, name, reloadSpeed, cost, range, targetModes);
        this.reloadSpeed = reloadSpeed;
        this.targetModes = targetModes;
        this.range = range;
        this.towersToAddList = towersToAddList;
    }

    private List<ITower> createMiniTowers() {
        ITower miniTower1 = new MechMiniTower(this.getX() + 100, this.getY() - 100, reloadSpeed, range, targetModes);
        ITower miniTower2 = new MechMiniTower(this.getX() - 100, this.getY() - 100, reloadSpeed, range, targetModes);

        miniTowers.add(miniTower1);
        miniTowers.add(miniTower2);
        return miniTowers;
    }

    @Override
    void createProjectile(List<IProjectile> projectileList) {
        // Empty for now, maybe move around robot towers here later?
    }

    @Override
    public void remove(List<ITower> towersList){
        towersList.removeAll(miniTowers);
        towersList.remove(this);
    }

    @Override
    public void update(List<IProjectile> projectilesList, float newAngle, boolean hasTarget) {
        if (this.isPlaced() && miniTowers.isEmpty()) {
            List<ITower> miniTowers = createMiniTowers();
            for (ITower miniTower : miniTowers) {
                miniTower.placeTower();
            }
            towersToAddList.addAll(miniTowers);
        }
        this.setAngle(0);
    }

}
