package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.IMapObject;

public interface IProjectile extends IMapObject {

    void setAngle(float newAngle);

    boolean getIfDealtDamage();

    void setDealtDamage(boolean bool);

    void virusIsHit();

    void move();

    String getName();




}
