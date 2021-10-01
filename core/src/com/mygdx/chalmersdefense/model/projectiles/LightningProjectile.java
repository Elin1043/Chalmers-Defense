package com.mygdx.chalmersdefense.model.projectiles;


/**
 * @author Elin Forsberg
 * Class representing a lightning projectile
 */
public class LightningProjectile extends Projectile{


    private int hitCountsLeft = 400000;

    public LightningProjectile(float x, float y, float angle, int upgradeLevel) {
        super(5 , "electroProjectile" + upgradeLevel, x, y, angle);
    }


    private void countVirusHit(){
        if (hitCountsLeft > 0){
            hitCountsLeft--;
        } else {
            this.setDealtDamage(true);
            super.canRemove = true;
        }
    }

    @Override
    public void virusIsHit(float angle){
            this.countVirusHit();
            if (angle >= 0) {
                this.setAngle(angle);
            }
    }


}
