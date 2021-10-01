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

    @Override
    public void virusIsHit(float angle) {
        // -60 because the acid pool image is 120x120 in size, and it needs to be placed centered
        projectileList.add(ProjectileFactory.createAcidPool(getX() - 60, getY() - 60, upgradeLevel));
    }





}
