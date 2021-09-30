package com.mygdx.chalmersdefense.model.projectiles;

public class AcidPool extends Projectile implements IProjectile{

    public AcidPool(int speed, String name, float x, float y, float angle) {
        super(speed, name, x, y, angle);
    }

    @Override
    public IProjectile createProjectile(int speed, float x, float y, float angle) {
        return null;
    }
}
