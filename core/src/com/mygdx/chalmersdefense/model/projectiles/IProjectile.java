package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

public interface IProjectile extends IMapObject {

    void setAngle(float newAngle);

    boolean getIfDealtDamage();

    void setDealtDamage(boolean bool);

    void virusIsHit( IVirus virus, List<IProjectile> list, List<IVirus> virusInRange);

    void move();

    String getName();


    boolean remove();
}
