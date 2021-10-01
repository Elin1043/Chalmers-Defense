package com.mygdx.chalmersdefense.model.projectiles;


/**
 * @author Elin Forsberg
 * Class representing a lightning projectile
 */
public class LightningProjectile extends Projectile{


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
    public boolean canRemove(){
        return false;
    }

    @Override
    public void virusIsHit(float angle){
            this.countVirusHit();
            this.setAngle(angle);

    }


}
