package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.utilities.CountDownTimer;

/**
 * @author Elin Forsberg
 * <p>
 * 2021-10-07 Modified by Joel Båtsman Hilmersson: Made class package private
 * <p>
 * Class representing a money projectile
 */

final class MoneyPile extends Projectile{
    // Timer over how long to show money animation
    private final CountDownTimer timer = new CountDownTimer(150);

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
