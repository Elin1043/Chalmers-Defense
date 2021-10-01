package com.mygdx.chalmersdefense.model.projectiles;

import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing an acid projectile
 */
public class AcidProjectile extends Projectile{
    private final int upgradeLevel;
    private final List<IProjectile> projectileList;


    public AcidProjectile(float x, float y, float angle, int upgradeLevel, List<IProjectile> projectileList) {
        //TODO Speed calc
        super(5 ,"chemistProjectile" + upgradeLevel, x, y, angle);
        this.upgradeLevel = upgradeLevel;
        this.projectileList = projectileList;
    }

//    @Override
//    public void virusIsHit() {
//        System.out.println("Tjenare");
//        projectileList.add(ProjectileFactory.createAcidPool(getX(), getY(), upgradeLevel));
//    }





}
