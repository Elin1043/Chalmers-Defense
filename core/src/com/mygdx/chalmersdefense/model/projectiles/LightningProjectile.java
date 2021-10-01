package com.mygdx.chalmersdefense.model.projectiles;


/**
 * @author Elin Forsberg
 * Class representing a lightning projectile
 */
public class LightningProjectile extends Projectile{


    private int hitCountsLeft = 4;
    private boolean canRemove = false;

    public LightningProjectile(float x, float y, float angle, int upgradeLevel) {
        super(5 , "electroProjectile" + upgradeLevel, x, y, angle);
    }


    private void countVirusHit(){
        if (hitCountsLeft > 0){
            hitCountsLeft--;
        } else {
            this.setDealtDamage(true);
            canRemove = true;
        }
    }

    @Override
    public boolean canRemove(){
        return canRemove;
    }

    @Override
    public void virusIsHit(float angle){
            this.countVirusHit();
            if (angle >= 0) {
                this.setAngle(angle);
            }
    }


}
