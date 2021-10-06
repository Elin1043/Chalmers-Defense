package com.mygdx.chalmersdefense.model.projectiles;


import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Factory for creating projectiles
 */
public abstract class ProjectileFactory {

    /**
     * Creates a projectile of type ZeroOneProjectile
     * @param towerX the x - coordinate of the tower shooting the projectile
     * @param towerY the y - coordinate of the tower shooting the projectile
     * @param angle the angle of the tower shooting the projectile
     * @param upgradeLevel the upgradeLevel of the tower shooting the projectile
     * @return the created projectile
     */
    static public IProjectile createZeroOneProjectile(float towerX, float towerY, float angle, int upgradeLevel){
        return new ZeroOneProjectile(towerX, towerY, angle, upgradeLevel);
    }

    /**
     * Creates a projectile of type AcidProjectile
     * @param towerX the x - coordinate of the tower shooting the projectile
     * @param towerY the y - coordinate of the tower shooting the projectile
     * @param angle the angle of the tower shooting the projectile
     * @param upgradeLevel the upgradeLevel of the tower shooting the projectile
     * @param projectileList the list of projectiles to add projectile to.
     * @return the created projectile
     */
    static public IProjectile createAcidProjectile(float towerX, float towerY, float angle, int upgradeLevel, List<IProjectile> projectileList){
        return new AcidProjectile(towerX, towerY, angle, upgradeLevel, projectileList);
    }


    /**
     * Creates a projectile of type AcidPool
     * @param poolX the x - coordinate of where the pool should be placed
     * @param poolY the y - coordinate of where the pool should be placed
     * @param upgradeLevel the upgradeLevel of the chemist tower
     * @return the created projectile
     */
    static public IProjectile createAcidPool(float poolX, float poolY, int upgradeLevel){
        return new AcidPool(poolX, poolY, upgradeLevel);
    }

    /**
     * Creates a projectile of type LightningProjectile
     * @param towerX the x - coordinate of the tower shooting the projectile
     * @param towerY the y - coordinate of the tower shooting the projectile
     * @param angle the angle of the tower shooting the projectile
     * @param upgradeLevel the upgradeLevel of the tower shooting the projectile
     * @return the created projectile
     */
    static public IProjectile createLightningProjectile(float towerX, float towerY, float angle, int upgradeLevel){
        return new LightningProjectile(towerX, towerY, angle, upgradeLevel);
    }

    /**
     * Creates a projectile of type RobotProjectile
     * @param towerX the x - coordinate of the tower shooting the projectile
     * @param towerY the y - coordinate of the tower shooting the projectile
     * @param angle the angle of the tower shooting the projectile
     * @param upgradeLevel the upgradeLevel of the tower shooting the projectile
     * @return the created projectile
     */
    static public IProjectile createRobotProjectile(float towerX, float towerY, float angle, int upgradeLevel){
        return new RobotProjectile(towerX, towerY, angle, upgradeLevel);
    }

    /**
     * Creates a projectile of type MoneyProjectile
     * @param towerX the x - coordinate of the tower shooting the projectile
     * @param towerY the y - coordinate of the tower shooting the projectile
     * @param upgradeLevel the upgradeLevel of the tower shooting the projectile
     * @return the created projectile
     */
    static public IProjectile createMoneyProjectile(float towerX, float towerY, int upgradeLevel){
        return new MoneyProjectile(towerX, towerY, upgradeLevel);
    }
}
