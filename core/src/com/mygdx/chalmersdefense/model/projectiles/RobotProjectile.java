package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.modelUtilities.CountDownTimer;

/**
 * @author Elin Forsberg
 * Class representing a robot projectile
 */
final class RobotProjectile extends Projectile {

    private final CountDownTimer hitCountsLeft; // Hit amount left before projectile can be removed

    /**
     * Creates a RobotProjectile object
     * @param x The x start position
     * @param y The y start position
     * @param angle The angle of the projectile
     * @param upgradeLevel The upgrade level of the projectile
     */
    RobotProjectile(float x, float y, float angle, int upgradeLevel) {
        super(5, "mechaProjectile" + upgradeLevel, x, y, angle, Math.max(1, upgradeLevel-1));
        hitCountsLeft = switch (upgradeLevel) {
            case 3 -> new CountDownTimer(1);
            default -> new CountDownTimer(0);
        };
    }


    //Helper method for counting how many viruses have been hit If hitCountsLeft is 0, then projectile can be removed.
    private void countVirusHit() {
        if (hitCountsLeft.haveReachedZero()) {
            super.canRemove = true;
        }
    }

    @Override
    void virusIsHit(int hitVirusHashCode, float angle) {
        super.haveHitList.add(hitVirusHashCode);
        this.countVirusHit();

        if (angle >= 0) {
            this.setAngle(angle);
        } else {
            super.canRemove = true;
        }
    }
}
