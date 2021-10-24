package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.modelUtilities.CountDownTimer;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Class representing an area effected by the matrix. Slows down viruses that comes in contact
 */
final class MatrixArea extends Projectile {

    private final CountDownTimer matrixTimer = new CountDownTimer(50); // Lifetime of MatrixArea

    /**
     * Creates a MatrixArea object
     *
     * @param x            The x start position
     * @param y            The y start position
     * @param upgradeLevel The upgrade level of the projectile
     */
    MatrixArea(float x, float y, int upgradeLevel) {
        super(0, "hackerArea" + upgradeLevel, x, y, 0, new float[]{0.75F, 0.75F, 0.5F}[upgradeLevel - 1]);
    }

    @Override
    public void update(boolean hasVirusBeenHit, int hitVirusHashCode, float angle) {
        if (matrixTimer.haveReachedZero()) {
            this.canRemove = true;
        }
        super.update(hasVirusBeenHit, hitVirusHashCode, angle);
    }

    @Override
    void virusIsHit(int haveHit, float angle) {
        super.haveHitList.add(haveHit);
    }
}
