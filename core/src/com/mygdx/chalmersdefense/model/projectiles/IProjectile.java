package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.IMapObject;


public interface IProjectile extends IMapObject {

    void update(boolean hitVirus, int haveHit, float angle);

    boolean canRemove();

    boolean haveHitBefore(int hashCode);
}
