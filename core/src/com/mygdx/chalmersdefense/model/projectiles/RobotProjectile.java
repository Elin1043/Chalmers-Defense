package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing a robot projectile
 */
final class RobotProjectile extends Projectile {

    RobotProjectile(float x, float y, float angle, int upgradeLevel) {
        super(5, "mechaProjectile" + upgradeLevel, x, y, angle, Math.max(1, upgradeLevel-1));
    }
}
