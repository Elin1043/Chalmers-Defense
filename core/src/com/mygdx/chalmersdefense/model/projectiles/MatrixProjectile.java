package com.mygdx.chalmersdefense.model.projectiles;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing Hackerman / HackerTowers shooting projectile
 */
class MatrixProjectile extends Projectile{

    private final List<IProjectile> projectileList; // The list to add the MatrixArea to

    MatrixProjectile(float x, float y, float angle, int upgradeLevel, List<IProjectile> projectileList) {
        super(7, "hackerProjectile" + upgradeLevel, x, y, angle);
        this.projectileList = projectileList;
    }


}
