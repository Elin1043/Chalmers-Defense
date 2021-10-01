package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.IMapObject;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

public interface IProjectile extends IMapObject {

    void setAngle(float newAngle);

    boolean getIfDealtDamage();

    void setDealtDamage(boolean bool);

    void update(boolean hitVirus, float angle);

    String getName();

    int getRange();


    boolean remove();
}
