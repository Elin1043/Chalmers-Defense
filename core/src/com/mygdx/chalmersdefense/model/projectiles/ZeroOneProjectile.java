package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing a specific projectile
 */
public class ZeroOneProjectile extends Projectile{
    public ZeroOneProjectile(float x, float y, float angle, int upgradelevel) {
        super(5 , "smurfProjectile" + upgradelevel, x, y, angle);

    }
}
