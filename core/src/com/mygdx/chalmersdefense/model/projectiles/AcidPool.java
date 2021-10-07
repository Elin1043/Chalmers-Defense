package com.mygdx.chalmersdefense.model.projectiles;


/**
 * @author Joel Båtsman Hilmersson
 * Class representing an acidPool projectile
 */
class AcidPool extends Projectile implements IProjectile {

    private int poolTimer = 150;    // Max Lifetime off acid pool
    private int maxVirusHit = 5;    // Max amount of virus that can be hit with the pool

    AcidPool(float x, float y, int upgradeLevel) {
        super(0, "chemistAcid" + upgradeLevel, x, y, 0);
    }

    @Override
    public void update(boolean hitVirus, int haveHit, float angle) {
        if (poolTimer <= 0) {
            this.canRemove = true;
        } else {
            poolTimer--;
        }
        super.update(hitVirus, haveHit, angle);
    }

    @Override
    void virusIsHit(int haveHit, float angle) {
        if (maxVirusHit > 0) {
            maxVirusHit--;
        } else {
            this.canRemove = true;
        }

        super.haveHitList.add(haveHit);
    }
}
