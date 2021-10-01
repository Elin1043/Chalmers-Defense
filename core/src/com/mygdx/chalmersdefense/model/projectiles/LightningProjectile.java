package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.utilities.Calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing a lightning projectile
 */
public class LightningProjectile extends Projectile{
    private int range = 150;

    private int hitCountsLeft = 4;

    public LightningProjectile(float x, float y, float angle, int upgradeLevel) {
        super(5 , "electroProjectile" + upgradeLevel, x, y, angle);
    }


    private void countVirusHit(){
        if (hitCountsLeft > 0){
            hitCountsLeft--;
        } else {
            this.setDealtDamage(true);
        }
    }

    @Override
    public boolean remove(){
        return false;
    }

    @Override
    public void virusIsHit(float angle){

            this.countVirusHit();
            this.setAngle(angle);

    }

    public int getRange() {
        return range;
    }
}
