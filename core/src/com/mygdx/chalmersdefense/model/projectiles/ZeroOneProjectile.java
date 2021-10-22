package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing a ZeroOneprojectile
 */
final class ZeroOneProjectile extends Projectile {

    ZeroOneProjectile(float x, float y, float angle, int upgradelevel) {
        super(5, "smurfProjectile" + upgradelevel, x, y, angle, 1);
    }

}
