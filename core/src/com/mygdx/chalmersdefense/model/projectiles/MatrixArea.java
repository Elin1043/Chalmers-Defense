package com.mygdx.chalmersdefense.model.projectiles;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Class representing an area effected by the matrix. Slows down viruses that comes in contact
 */
public class MatrixArea extends Projectile {

    private int matrixTimer = 50;    // Lifetime of MatrixArea

    MatrixArea(float x, float y, int upgradeLevel) {
        super(0, "hackerArea" + upgradeLevel, x, y, 0, 0.5F);
    }

    @Override
    public void update(boolean hasVirusBeenHit, int hitVirusHashCode, float angle) {
        if (matrixTimer <= 0) {
            this.canRemove = true;
        } else {
            matrixTimer--;
        }
        super.update(hasVirusBeenHit, hitVirusHashCode, angle);
    }

    @Override
    void virusIsHit(int haveHit, float angle) {
        super.haveHitList.add(haveHit);
    }
}
