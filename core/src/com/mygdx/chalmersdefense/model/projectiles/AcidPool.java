package com.mygdx.chalmersdefense.model.projectiles;


import com.mygdx.chalmersdefense.utilities.CountDownTimer;

/**
 * @author Joel Båtsman Hilmersson
 * Class representing an acidPool projectile
 */
final class AcidPool extends Projectile{

    private final CountDownTimer poolTimer = new CountDownTimer(150); // Max Lifetime off acid pool
    private final CountDownTimer maxVirusHit; // Max amount of virus that can be hit with the pool

    /**
     * Creates an AcidPool object
     * @param x The x start position
     * @param y The y start position
     * @param upgradeLevel The upgrade level of the projectile
     */
    AcidPool(float x, float y, int upgradeLevel) {
        super(0, "chemistAcid" + upgradeLevel, x, y, 0, 1);
        maxVirusHit = switch (upgradeLevel) {
            case 1 -> new CountDownTimer(2);
            case 2 -> new CountDownTimer(4);
            default -> new CountDownTimer(10);
        };
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
