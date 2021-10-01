package com.mygdx.chalmersdefense.model.projectiles;


/**
 * @author Elin Forsberg
 * Class representing a lightning projectile
 */
public class LightningProjectile extends Projectile{


    private int hitCountsLeft = 5;

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
    public void virusIsHit(int haveHit, float angle){
        super.haveHitList.add(haveHit);
        this.countVirusHit();
        if (angle >= 0) {
            this.setAngle(angle);
        }
    }


}
