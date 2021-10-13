package com.mygdx.chalmersdefense.model.projectiles;


import com.mygdx.chalmersdefense.utilities.CountDownTimer;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing an acidPool projectile
 */
class AcidPool extends Projectile{

    //private int poolTimer = 150;    // Max Lifetime off acid pool
    private final CountDownTimer poolTimer = new CountDownTimer(150);

    //private int maxVirusHit = 5;    // Max amount of virus that can be hit with the pool
    private final CountDownTimer maxVirusHit = new CountDownTimer(5);

    AcidPool(float x, float y, int upgradeLevel) {
        super(0, "chemistAcid" + upgradeLevel, x, y, 0, 1);
    }

    @Override
    public void update(boolean hasVirusBeenHit, int hitVirusHashCode, float angle) {
        if (poolTimer.haveReachedZero()) { this.canRemove = true; }
        super.update(hasVirusBeenHit, hitVirusHashCode, angle);
    }

    @Override
    void virusIsHit(int hitVirusHashCode, float angle) {
        if (maxVirusHit.haveReachedZero()) { this.canRemove = true; }
        super.haveHitList.add(hitVirusHashCode);
    }
}
