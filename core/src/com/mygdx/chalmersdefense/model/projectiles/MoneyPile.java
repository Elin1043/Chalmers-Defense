package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 * <p>
 * 2021-10-07 Modified by Joel Båtsman Hilmersson: Made class package private
 * <p>
 * Class representing a money projectile
 */

class MoneyPile extends Projectile implements IProjectile {
    private int moneyTimer = 150;   // Timer over how long to show money animation


    MoneyPile(float x, float y, int upgradeLevel) {
        super(0.1F, "money" + upgradeLevel, x + 10, y + 80, 90);
    }

    @Override
    public void update(boolean hasVirusBeenHit, int hitVirusHashCode, float angle) {
        if (moneyTimer <= 0) {
            this.canRemove = true;
        } else {
            moneyTimer--;
        }
        super.update(false, hitVirusHashCode, angle);
    }

    @Override
    public boolean haveHitBefore(int hashCode) {
        return true;
    }


}
