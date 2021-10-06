package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Elin Forsberg
 *
 * 2021-10-07 Modified by Joel Båtsman Hilmersson: Made class package private
 *
 * Class representing a money projectile
 */

class MoneyProjectile extends Projectile implements IProjectile{
    private int moneyTimer = 150;


    MoneyProjectile(float x, float y , int upgradeLevel) {
        super(0.1F, "money"+ upgradeLevel, x + 10, y + 80, 90);
    }

    @Override
    public void update(boolean hitVirus, int haveHit, float angle){
        if (moneyTimer <= 0) {
            this.canRemove = true;
        } else {
            moneyTimer--;
        }
        super.update(false, haveHit, angle);
    }

    @Override
    public boolean haveHitBefore(int hashCode){
        return true;
    }


}
