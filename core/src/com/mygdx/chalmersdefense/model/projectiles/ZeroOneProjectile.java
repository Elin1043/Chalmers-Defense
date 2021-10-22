package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing a ZeroOneprojectile
 */
final class ZeroOneProjectile extends Projectile {

    /**
     * Creates a ZeroOneProjectile object
     * @param x The x start position
     * @param y The y start position
     * @param angle The angle of the projectile
     * @param upgradeLevel The upgrade level of the projectile
     */
    ZeroOneProjectile(float x, float y, float angle, int upgradeLevel) {
        super(5, "smurfProjectile" + upgradeLevel, x, y, angle, 1);
    }

}
