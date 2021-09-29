package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing a lightning projectile
 */
public class LightningProjectile extends Projectile{
    private static String name = "electroProjectile1"; //Change when have new projectile pictures
    private int range = 150;

    public LightningProjectile(int speed, float x, float y, double angle) {
        super(speed, name, x, y, angle);
    }

    @Override
    public Projectile createProjectile(int speed, float x, float y, double angle) {
        return new LightningProjectile(speed, x, y, angle);
    }

    public int getRange() {
        return range;
    }
}
