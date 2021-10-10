package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing Hackerman / HackerTowers shooting projectile
 */
class MatrixProjectile extends Projectile{

    MatrixProjectile(float x, float y, float angle, int upgradeLevel) {
        super(7, "hackerProjectile" + upgradeLevel, x, y, angle);
    }


}
