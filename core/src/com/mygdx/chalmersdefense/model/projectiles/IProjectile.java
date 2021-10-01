package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.IMapObject;


public interface IProjectile extends IMapObject {

    boolean getIfDealtDamage();

    void setDealtDamage(boolean bool);

    void update(boolean hitVirus, int haveHit, float angle);

    String getName();

    boolean canRemove();

    boolean haveHitBefore(int hashCode);
}
