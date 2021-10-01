package com.mygdx.chalmersdefense.model.projectiles;

public class AcidPool extends Projectile implements IProjectile{

    public AcidPool(float x, float y, int upgradeLevel) {
        super(0, "acidPool" + upgradeLevel, x, y, 0);
    }



//    @Override
//    public IProjectile createProjectile(int speed, float x, float y, float angle) {
//        return null;
//    }
}
