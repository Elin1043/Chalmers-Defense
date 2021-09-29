package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing an acid projectile
 */
public class AcidProjectile extends Projectile{
    private static String name = "chemistProjectile"; //Change when have new projectile pictures
    private int range = 10;

    public AcidProjectile(int speed, float x, float y, float angle) {
        super(speed, name, x, y, angle);

    }

    @Override
    public Projectile createProjectile(int speed, float x, float y, float angle) {
        return new AcidProjectile(speed, x, y, angle);
    }

    public int getRange() {
        return range;
    }



}
