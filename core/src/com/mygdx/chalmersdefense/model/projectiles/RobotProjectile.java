package com.mygdx.chalmersdefense.model.projectiles;

public class RobotProjectile extends Projectile{
    private static String name = "mechaProjectile";

    public RobotProjectile(int speed, float x, float y, double angle) {
        super(speed, name, x, y, angle);
    }

    @Override
    public Projectile createProjectile(int speed, float x, float y, double angle) {
        return new RobotProjectile(speed, x, y, angle);
    }
}
