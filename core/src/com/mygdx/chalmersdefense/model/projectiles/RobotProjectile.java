package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing a robot projectile
 */
public class RobotProjectile extends Projectile{
    private static String name = "mechaProjectile";

    public RobotProjectile(float x, float y, float angle) {
        super(5 , name, x, y, angle);
    }

//    @Override
//    public IProjectile createProjectile(int speed, float x, float y, float angle) {
//        return new RobotProjectile(x, y, angle);
//    }
}
