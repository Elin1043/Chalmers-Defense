package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.Virus;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Factory for projectiles
 */
public abstract class ProjectileFactory {

    static public IProjectile createZeroOneProjectile(float towerX, float towerY, float angle, int upgradeLevel){
        return new ZeroOneProjectile(towerX, towerY, angle, upgradeLevel);
    }

    static public IProjectile createAcidProjectile(float towerX, float towerY, float angle, List<IProjectile> projectileList, int upgradeLevel){
        return new AcidProjectile(towerX, towerY, angle, projectileList, upgradeLevel);
    }
    static public IProjectile createAcidPool(float poolX, float poolY, int upgradeLevel){
        return new AcidPool(poolX, poolY, upgradeLevel);
    }
//    static public IProjectile createBulletProjectile(int speed, float towerX, float towerY, float angle){
//        return;
//    }
//    static public IProjectile createLightningProjectile(int speed, float towerX, float towerY, float angle){
//        return;
//    }

}
