package com.mygdx.chalmersdefense.model.projectiles;


/**
 * @author Elin Forsberg
 * Class representing a lightning projectile
 */
class LightningProjectile extends Projectile {

    private int hitCountsLeft = 4;  // Hit amount left before projectile can be removed

    LightningProjectile(float x, float y, float angle, int upgradeLevel) {
        super(5, "electroProjectile" + upgradeLevel, x, y, angle, 1);
    }


    /**
     * Helper method for counting how many viruses have been hit
     * If hitCountsLeft is 0, then projectile can be removed.
     */
    private void countVirusHit() {
        if (hitCountsLeft > 0) {
            hitCountsLeft--;
        } else {
            super.canRemove = true;
        }
    }

    @Override
    public void virusIsHit(int haveHit, float angle) {
        super.haveHitList.add(haveHit);
        this.countVirusHit();

        if (angle >= 0) {
            this.setAngle(angle);
        } else {
            super.canRemove = true;
        }
    }


}
