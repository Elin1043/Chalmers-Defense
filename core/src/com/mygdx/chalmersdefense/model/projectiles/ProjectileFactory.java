package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.Virus;

public abstract class ProjectileFactory {

    static public IProjectile createAcidProjectile(){
        return new AcidProjectile();
    }
    static public IProjectile createAcidPool(){
        return
    }
    static public IProjectile createBulletProjectile(){
        return
    }
    static public IProjectile createLightningProjectile(){
        return
    }

}
