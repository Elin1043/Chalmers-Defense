package com.mygdx.chalmersdefense.model.projectiles;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing an acid projectile
 */
public class AcidProjectile extends Projectile{
    private int range = 10;

    public AcidProjectile(float x, float y, float angle, List<IProjectile> projectileList, int upgradeLevel) {
        //TODO Speed calc
        super(5 ,"chemistProjectile" + upgradeLevel, x, y, angle);

    }

//    @Override
//    public IProjectile createProjectile(int speed, float x, float y, float angle) {
//        return new AcidProjectile(speed, x, y, angle, projectileList);
//    }

    public int getRange() {
        return range;
    }



}
