package com.mygdx.chalmersdefense.model.viruses;

import com.mygdx.chalmersdefense.model.IMapObject;

public interface IVirus extends IMapObject {

    void  update();

    boolean isDead();

    int getLifeDecreaseAmount();

    float getTotalDistanceTraveled();

    boolean getIfGotHit();

    void setGotHit(boolean gotHit);

    void decreaseHealth();

}
