package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * Class representing a lightning projectile
 */
public class LightningProjectile extends Projectile{
    private int range = 150;

    public LightningProjectile(float x, float y, float angle, int upgradeLevel) {
        super(5 , "electroProjectile" + upgradeLevel, x, y, angle);
    }

//    @Override
//    public IProjectile createProjectile(int speed, float x, float y, float angle) {
//        return new LightningProjectile(speed, x, y, angle);
//    }

    public int getRange() {
        return range;
    }
}
