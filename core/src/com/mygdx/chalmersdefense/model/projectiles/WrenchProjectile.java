package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author  Joel Båtsman Hilmersson
 * Class representing a WrenchProjectile
 */
public class WrenchProjectile extends Projectile {

    /**
     * Creates a WrenchProjectile object
     * @param towerX The x start position
     * @param towerY The y start position
     * @param angle The angle of the projectile
     */
    WrenchProjectile(float towerX, float towerY, float angle) {
        super(5, "wrenchProjectile", towerX, towerY, angle, 1);
    }
}
