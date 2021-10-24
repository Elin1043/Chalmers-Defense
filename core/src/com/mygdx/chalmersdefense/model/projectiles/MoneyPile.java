package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.modelUtilities.CountDownTimer;

/**
 * @author Elin Forsberg
 * <p>
 * 2021-10-07 Modified by Joel Båtsman Hilmersson: Made class package private
 * <p>
 * Class representing money pile
 */

final class MoneyPile extends Projectile {

    private final CountDownTimer timer = new CountDownTimer(150); // Timer over how long to show money animation

    /**
     * Creates a MoneyPile object
     *
     * @param x            The x start position
     * @param y            The y start position
     * @param upgradeLevel The upgrade level of the projectile
     */
    MoneyPile(float x, float y, int upgradeLevel) {
        super(0.1F, "money" + upgradeLevel, x + 10, y + 80, 90, 1);
    }

    @Override
    public void update(boolean hasVirusBeenHit, int hitVirusHashCode, float angle) {
        if (timer.haveReachedZero()) {
            this.canRemove = true;
        }
        super.update(false, hitVirusHashCode, angle);
    }

    @Override
    public boolean haveHitBefore(int hashCode) {
        return true;
    }


}
