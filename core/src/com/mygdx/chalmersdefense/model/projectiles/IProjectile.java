package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.IMapObject;

public interface IProjectile extends IMapObject {

    void setAngle(float newAngle);

    Projectile createProjectile();

    boolean getDealtDamage();

    void setDealtDamage(boolean bool);

    void virusHit();




}
