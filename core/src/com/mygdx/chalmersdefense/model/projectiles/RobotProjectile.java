package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing a robot projectile
 */
public class RobotProjectile extends Projectile{
    private static String name = "mechaProjectile";

    public RobotProjectile(int speed, float x, float y, float angle) {
        super(speed, name, x, y, angle);
    }

    @Override
    public Projectile createProjectile(int speed, float x, float y, float angle) {
        return new RobotProjectile(speed, x, y, angle);
    }
}
