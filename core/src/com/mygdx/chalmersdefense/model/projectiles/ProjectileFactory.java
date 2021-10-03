package com.mygdx.chalmersdefense.model.projectiles;


import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Factory for creating projectiles
 */
public abstract class ProjectileFactory {

    static public IProjectile createZeroOneProjectile(float towerX, float towerY, float angle, int upgradeLevel){
        return new ZeroOneProjectile(towerX, towerY, angle, upgradeLevel);
    }

    static public IProjectile createAcidProjectile(float towerX, float towerY, float angle, int upgradeLevel, List<IProjectile> projectileList){
        return new AcidProjectile(towerX, towerY, angle, upgradeLevel, projectileList);
    }

    static public IProjectile createAcidPool(float poolX, float poolY, int upgradeLevel){
        return new AcidPool(poolX, poolY, upgradeLevel);
    }

    static public IProjectile createLightningProjectile(float towerX, float towerY, float angle, int upgradeLevel){
        return new LightningProjectile(towerX, towerY, angle, upgradeLevel);
    }

    static public IProjectile createRobotProjectile(float towerX, float towerY, float angle, int upgradeLevel){
        return new RobotProjectile(towerX, towerY, angle, upgradeLevel);
    }
}
