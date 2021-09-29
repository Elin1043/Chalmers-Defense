package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing a specific projectile
 */
public class BulletProjectile extends Projectile{
    private static String name = "smurfProjectile1";


    public BulletProjectile(int speed , float x, float y, float angle) {
        super(speed, name, x, y, angle);

    }

    @Override
    public IProjectile createProjectile(int speed, float x, float y, float angle) {
        return new BulletProjectile(speed, x, y, angle);
    }
}
