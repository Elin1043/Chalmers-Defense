package com.mygdx.chalmersdefense.model.viruses;

import com.mygdx.chalmersdefense.model.path.Path;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing boss virus
 */
final public class BossVirus extends Virus{


    private final Path path; // The path to send to new viruses
    private final List<IVirus> virusList; // The virus list to add new viruses to

    /**
     * Creates Virus object
     *
     * @param path The path to follow
     */
    BossVirus(Path path, List<IVirus> virusList) {
        super(50, path);
        this.path = path;
        this.virusList = virusList;
    }

    @Override
    public void decreaseHealth(float damage) {
        super.decreaseHealth(damage);
        if (isDead()){
            for (int i = 0; i < 5; i++) {
                virusList.add(new StandardVirus(5, path, getPos()[0] + getWidth()/2F, getPos()[1] + getHeight()/2F, getCurrentMoveToVectorIndex()));
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
