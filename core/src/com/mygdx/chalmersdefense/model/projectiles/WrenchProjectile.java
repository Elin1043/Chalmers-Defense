package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author  Joel Båtsman Hilmersson
 * Class representing a WrenchProjectile
 */
public class WrenchProjectile extends Projectile {

    WrenchProjectile(float towerX, float towerY, float angle) {
        super(5, "wrenchProjectile", towerX, towerY, angle, 1);
    }
}
