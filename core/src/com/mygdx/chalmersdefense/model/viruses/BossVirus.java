package com.mygdx.chalmersdefense.model.viruses;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing boss virus
 */
final class BossVirus extends Virus{

    private final List<IVirus> virusList; // The virus list to add new viruses to

    /**
     * Creates Virus object
     *
     * @param virusList viruslist to add viruses to
     */
    BossVirus(List<IVirus> virusList) {
        super(50);
        this.virusList = virusList;
    }

    @Override
    public void decreaseHealth(float damage) {
        super.decreaseHealth(damage);
        if (isDead()){
            for (int i = 0; i < 5; i++) {
                virusList.add(new StandardVirus(5, getPos()[0] + getWidth()/2F, getPos()[1] + getHeight()/2F, getCurrentMoveToVectorIndex()));
            }
        }
    }

    @Override
    void slowDownEffect(float slowdown){
        float newSlowdown = slowdown;
        newSlowdown *= (1 + (1 - slowdown));
        super.slowDownEffect(newSlowdown);
    }

    @Override
    double getTotalVirusSpeed() {
        return 0.8 * getSlowdown();
    }


}
