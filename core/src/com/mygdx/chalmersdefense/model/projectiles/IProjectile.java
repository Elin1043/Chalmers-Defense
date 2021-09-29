package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.IMapObject;

public interface IProjectile extends IMapObject {

    void setAngle(float newAngle);

    IProjectile createProjectile(int speed, float x, float y, float angle);

    boolean getIfDealtDamage();

    void setDealtDamage(boolean bool);

    void virusHit();

    void move();

    String getName();




}
