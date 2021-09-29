package com.mygdx.chalmersdefense.model.viruses;

import com.mygdx.chalmersdefense.model.IMapObject;

public interface IVirus extends IMapObject {

    void  update();

    boolean isDead();

    int getLifeDecreaseAmount();

    float getTotalDistanceTravled();

    boolean gotHit();

}
