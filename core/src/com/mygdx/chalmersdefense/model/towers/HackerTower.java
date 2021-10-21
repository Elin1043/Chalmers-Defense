package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;

import java.util.List;

/**
 * @author Elin Forsberg
 *
 * Class representing the HackerTower
 *
 * 2021-10-10 Modified by Joel Båtsman Hilmersson: Made Hackertower use MatrixProjectile <br>
 */
final class HackerTower extends Tower {

    private final List<IProjectile> addToList;  // List to add new projectiles to

    HackerTower(float x, float y, String name, int reloadSpeed, int cost, int range, List<IProjectile> addToList) {
        super(x, y, name, reloadSpeed, cost, range);
        this.addToList = addToList;
    }


    @Override
    void createProjectile(List<IProjectile> projectileList) {
        projectileList.add(ProjectileFactory.createMatrixProjectile(getX(), getY(), getAngle(), getUpgradeLevel(), addToList));
    }
}
