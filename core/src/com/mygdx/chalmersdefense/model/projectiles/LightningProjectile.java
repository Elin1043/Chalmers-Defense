package com.mygdx.chalmersdefense.model.projectiles;

public class LightningProjectile extends Projectile{
    private static String name = "electroProjectile1"; //Change when have new projectile pictures
    private int range = 100;

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
