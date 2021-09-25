package com.mygdx.chalmersdefense.model.projectiles;

public class AcidProjectile extends Projectile{
    private static String name = "bullet";
    private int range = 10;

    public AcidProjectile(int speed, float x, float y, double angle) {
        super(speed, name, x, y, angle);

    }

    @Override
    public Projectile createProjectile(int speed, float x, float y, double angle) {
        return new AcidProjectile(speed, x, y, angle);
    }

    public int getRange() {
        return range;
    }
}
